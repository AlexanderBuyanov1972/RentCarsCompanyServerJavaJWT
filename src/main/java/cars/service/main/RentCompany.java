package cars.service.main;

import cars.dao.*;
import cars.dto.main.*;
import cars.entities.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ManagedResource("RentCompany:name=RentCompanyLTD")
public class RentCompany extends AbstractRentCompany {
	@Value("${finePercent:20}")
	int finePercent;
	@Value("${gasPrice:20}")
	int gasPrice;

	@ManagedAttribute
	public int getFine_percent() {
		return finePercent;
	}

	@ManagedAttribute
	public void setFine_percent(int fine_percent) {
		this.finePercent = fine_percent;
	}

	@ManagedAttribute
	public int getGas_price() {
		return gasPrice;
	}

	@ManagedAttribute
	public void setGas_price(int gas_price) {
		this.gasPrice = gas_price;
	}

	@Autowired
	CarRepository carRepository;
	@Autowired
	DriverRepository driverRepository;
	@Autowired
	ModelRepository modelRepository;
	@Autowired
	RecordRepository recordRepository;

	@Override
	@Transactional
	public CarsReturnCode addModel(ModelDto modelDto) {
		String modelName = modelDto.getModelName();
		if (modelRepository.existsById(modelName))
			return CarsReturnCode.MODEL_EXISTS;
		Model model = new Model(modelDto.getModelName(), modelDto.getGasTank(), modelDto.getCompany(),
				modelDto.getCountry(), modelDto.getPriceDay());
		modelRepository.save(model);
		return CarsReturnCode.OK;
	}

	@Override
	@Transactional
	public CarsReturnCode addCar(CarDto carDto) {
		String regNumber = carDto.getRegNumber();
		if (carRepository.existsById(regNumber))
			return CarsReturnCode.CAR_EXISTS;
		String modelName = carDto.getModelName();
		Model model = modelRepository.findById(modelName).orElse(null);
		if (model == null)
			return CarsReturnCode.NO_MODEL;
		Car car = new Car(carDto.getRegNumber(), carDto.getColor(), carDto.getState(), carDto.isInUse(),
				carDto.isFlRemoved(), model);
		carRepository.save(car);
		return CarsReturnCode.OK;
	}

	@Override
	@Transactional
	public CarsReturnCode addDriver(DriverDto driverDto) {
		Long licenseId = driverDto.getLicenseId();
		if (driverRepository.existsById(licenseId))
			return CarsReturnCode.DRIVER_EXISTS;
		Driver driver = new Driver(licenseId, driverDto.getName(), driverDto.getBirthYear(), driverDto.getPhone());
		driverRepository.save(driver);
		return CarsReturnCode.OK;
	}

	@Override
	public ModelDto getModel(String modelName) {
		Model model = modelRepository.findById(modelName).orElse(null);
		if (model == null)
			return null;
		return getModelDto(model);
	}

	private ModelDto getModelDto(Model model) {
		return new ModelDto(model.getModelName(), model.getGasTank(), model.getCompany(), model.getCountry(),
				model.getPriceDay());
	}

	@Override
	public CarDto getCar(String carNumber) {
		Car car = carRepository.findById(carNumber).orElse(null);
		if (car == null)
			return null;
		return getCarDto(car);
	}

	private CarDto getCarDto(Car car) {
		return new CarDto(car.getRegNumber(), car.getColor(), car.getModel().getModelName());
	}

	@Override
	public DriverDto getDriver(long licenseId) {
		Driver driver = driverRepository.findById(licenseId).orElse(null);
		if (driver == null)
			return null;
		return getDriverDto(driver);
	}

	private DriverDto getDriverDto(Driver driver) {
		return new DriverDto(driver.getLicenseId(), driver.getName(), driver.getBirthYear(), driver.getPhone());
	}

	@Override
	@Transactional
	public CarsReturnCode rentCar(RecordDto recordDto) {
		String carNumber = recordDto.getCarNumber();
		Long licenseId = recordDto.getLicenseId();
		LocalDate rentDate = recordDto.getRentDate();
		int rentDays = recordDto.getRentDays();
		Car car = carRepository.findById(carNumber).orElse(null);
		if (car == null || car.isFlRemoved() == true)
			return CarsReturnCode.NO_CAR;
		if (car.isInUse() == true)
			return CarsReturnCode.CAR_IN_USE;
		Driver driver = driverRepository.findById(licenseId).orElse(null);
		if (driver == null)
			return CarsReturnCode.NO_DRIVER;
		Record record = new Record(driver, car, rentDate, rentDays);
		car.setInUse(true);
		recordRepository.save(record);
		return CarsReturnCode.OK;
	}

	@Override
	@Transactional
	public CarsReturnCode returnCar(RecordDto recordDto) {
		String carNumber = recordDto.getCarNumber();
		LocalDate returnDate = recordDto.getReturnDate();
		int gasTankPercent = recordDto.getGasTankPercent();
		int damages = recordDto.getDamages();

		Record record = getRentRecord(carNumber);
		if (record == null)
			return CarsReturnCode.CAR_NOT_RENTED;
		if (returnDate.isBefore(record.getRentDate()))
			return CarsReturnCode.RETURN_DATE_WRONG;

		record.setDamages(damages);
		record.setGasTankPercent(gasTankPercent);
		record.setReturnDate(returnDate);

		Car car = carRepository.findById(carNumber).get();
		CarDto carDto = getCarDto(car);
		setCost(record, carDto);

		updateCarData(damages, car);
		recordRepository.save(record);
		return CarsReturnCode.OK;
	}

	private Record getRentRecord(String carNumber) {
		return recordRepository.findAll().stream()
				.filter(r -> r.getCar().getRegNumber().equals(carNumber) && r.getReturnDate() == null).findFirst()
				.orElse(null);
	}

	private void updateCarData(int damages, Car car) {
		if (damages > 0 && damages < 10)
			car.setState(State.GOOD);
		else if (damages >= 10 && damages < 30)
			car.setState(State.BAD);
		else if (damages >= 30)
			car.setFlRemoved(true);
		car.setInUse(false);
	}

	private void setCost(Record record, CarDto carDto) {
		long period = ChronoUnit.DAYS.between(record.getRentDate(), record.getReturnDate());
		float costPeriod = 0;
		ModelDto modelDto = getModel(carDto.getModelName());
		if (modelDto == null)
			throw new RuntimeException("Information model inconsistency (the car exists but model doesn't)");
		float costGas = 0;
		costPeriod = getCostPeriod(record, period, modelDto);
		costGas = getCostGas(record, modelDto);
		record.setCost(costPeriod + costGas);

	}

	private float getCostGas(Record record, ModelDto modelDto) {
		float costGas;
		int gasTank = modelDto.getGasTank();
		float litersCost = (float) (100 - record.getGasTankPercent()) * gasTank / 100;
		costGas = litersCost * getGasPrice();
		return costGas;
	}

	private float getCostPeriod(Record record, long period, ModelDto modelDto) {
		float costPeriod;
		long delta = period - record.getRentDays();
		float additionalPeriodCost = 0;
		int pricePerDay = modelDto.getPriceDay();
		int rentDays = record.getRentDays();
		if (delta > 0) {
			additionalPeriodCost = getAdditionalPeriodCost(pricePerDay, delta);
		}
		costPeriod = rentDays * pricePerDay + additionalPeriodCost;
		return costPeriod;
	}

	private float getAdditionalPeriodCost(int pricePerDay, long delta) {
		float fineCostPerDay = pricePerDay * getFinePercent() / 100;
		return (pricePerDay + fineCostPerDay) * delta;
	}

	@Override
	@Transactional
	public CarsReturnCode removeCar(String carNumber) {
		Car car = carRepository.findById(carNumber).orElse(null);
		if (car == null)
			return CarsReturnCode.NO_CAR;
		if (car.isInUse())
			return CarsReturnCode.CAR_IN_USE;
		car.setFlRemoved(true);
		carRepository.save(car);
		return CarsReturnCode.OK;
	}

//------------------clean----------------
	@Override
	@Transactional
	public List<CarDto> clear(LocalDate currentDate, int days) {
//		LocalDate dateDelete = currentDate.minusDays(days); 
//		List<Record> recordsForDelete = recordRepository.findByCarFlRemovedTrueAndReturnDateBefore(dateDelete);
//		List<Car> carsForDelete = recordsForDelete.stream().map(r -> r.getCar()).distinct()
//				.collect(Collectors.toList());
//		carsForDelete.forEach(carRepository::delete);
//		return carsForDelete.stream().map(this::getCarDto).collect(Collectors.toList());
		LocalDate dateDelete = currentDate.minusDays(days);
		List<Car> listCarsForDelete = recordRepository.removeCars(dateDelete);
//		carsForDelete.forEach(carRepository::delete);
		return listCarsForDelete.stream().map(this::getCarDto).collect(Collectors.toList());
	}
//-----------------------------------------
	@Override
	@Transactional
	public List<DriverDto> getCarDrivers(String carNumber) {
		return recordRepository.findAllByCarRegNumber(carNumber).stream().map(Record::getDriver).distinct()
				.map(this::getDriverDto).collect(Collectors.toList());

	}

	@Override
	@Transactional
	public List<CarDto> getDriverCars(Long licenseId) {
		return recordRepository.findAllByDriverLicenseId(licenseId).stream().map(Record::getCar).distinct()
				.map(this::getCarDto).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<String> getAllModelNames() {
		return modelRepository.getAllNames().collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<CarDto> getAllCars() {
		return carRepository.findAllBy().map(this::getCarDto).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<DriverDto> getAllDrivers() {
		return driverRepository.findAllBy().map(this::getDriverDto).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<RecordDto> getAllRecords() {
		return recordRepository.findAllBy().map(this::getRecordDto).collect(Collectors.toList());
	}

	private RecordDto getRecordDto(Record record) {
		return new RecordDto(record.getDriver().getLicenseId(),
				record.getCar().getRegNumber(),
				record.getRentDate(),
				record.getReturnDate(),
				record.getGasTankPercent(),
				record.getRentDays(),
				record.getCost(),
				record.getDamages());


	}

	@Override
	public List<String> getMostPopularModelNames() {
		long countMax = recordRepository.getCountMaxForMostPopular();
		return recordRepository.getListMostPopular(countMax);
	}

	@Override
	public List<String> getMostProfitModelNames() {
		Double profitMax = recordRepository.getMaxProfitFromModelNames();
		return recordRepository.getListStringMostProfitModelNames(profitMax);
	}

	@Override
	public Double getModelProfit(String modelName) {
		return recordRepository.getProfitFromModelName(modelName);
	}

}
