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
import java.time.LocalDateTime;
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

	String MODEL_IS_EXISTS = "model is exists";
	String OK = "OK";
	String CAR_IS_EXISTS = "car is exists";
	String DRIVER_EXISTS = "driver exists";
	String CAR_IN_USE = "car in use";
	String CAR_NOT_RENTED = "car not rented";
	String RETURN_DATE_WRONG = "return date wrong";
	String CAR_IS_NOT_EXISTS = "car is not exists";
	String MODEL_IS_NOT_EXISTS = "model is not exists";
	String DRIVER_IS_NOT_EXISTS = "driver is not exists";

	int goodCode = 200;

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
	public Response addModel(ModelDto modelDto) {
		String modelName = modelDto.getModelName();
		if (modelRepository.existsById(modelName)) {
			return new Response(null, MODEL_IS_EXISTS, goodCode, LocalDateTime.now().toString());
		}
		Model model = new Model(modelDto.getModelName(), modelDto.getGasTank(), modelDto.getCompany(),
				modelDto.getCountry(), modelDto.getPriceDay());
		modelRepository.save(model);
		return new Response(null, OK, goodCode, LocalDateTime.now().toString());
	}

	@Override
	@Transactional
	public Response addCar(CarDto carDto) {
		String regNumber = carDto.getRegNumber();
		if (carRepository.existsById(regNumber))
			return new Response(null, CAR_IS_EXISTS, goodCode, LocalDateTime.now().toString());
		String modelName = carDto.getModelName();
		Model model = modelRepository.findById(modelName).orElse(null);
		if (model == null)
			return new Response(null, MODEL_IS_NOT_EXISTS, goodCode, LocalDateTime.now().toString());
		Car car = new Car(carDto.getRegNumber(), carDto.getColor(), carDto.getState(), carDto.isInUse(),
				carDto.isFlRemoved(), model);
		carRepository.save(car);
		return new Response(null, OK, goodCode, LocalDateTime.now().toString());
	}

	@Override
	@Transactional
	public Response addDriver(DriverDto driverDto) {
		Long licenseId = driverDto.getLicenseId();
		if (driverRepository.existsById(licenseId))
			return new Response(null, DRIVER_EXISTS, goodCode, LocalDateTime.now().toString());
		Driver driver = new Driver(licenseId, driverDto.getName(), driverDto.getBirthYear(), driverDto.getPhone());
		driverRepository.save(driver);
		return new Response(null, OK, goodCode, LocalDateTime.now().toString());
	}

	@Override
	public Response getModel(String modelName) {
		Model model = modelRepository.findById(modelName).orElse(null);
		if (model == null)
			return new Response(null, MODEL_IS_NOT_EXISTS, goodCode, LocalDateTime.now().toString());
		return new Response(getModelDto(model), OK, goodCode, LocalDateTime.now().toString());
	}

	@Override
	public Response getCar(String carNumber) {
		Car car = carRepository.findById(carNumber).orElse(null);
		if (car == null)
			return new Response(null, CAR_IS_NOT_EXISTS, goodCode, LocalDateTime.now().toString());
		return new Response(getCarDto(car), OK, goodCode, LocalDateTime.now().toString());
	}

	private CarDto getCarDto(Car car) {
		return new CarDto(car.getRegNumber(), car.getColor(), car.getModel().getModelName());
	}

	@Override
	public Response getDriver(long licenseId) {
		Driver driver = driverRepository.findById(licenseId).orElse(null);
		if (driver == null)
			return new Response(null, DRIVER_IS_NOT_EXISTS, goodCode, LocalDateTime.now().toString());
		return new Response(getDriverDto(driver), OK, goodCode, LocalDateTime.now().toString());
	}

	private DriverDto getDriverDto(Driver driver) {
		return new DriverDto(driver.getLicenseId(), driver.getName(), driver.getBirthYear(), driver.getPhone());
	}

	@Override
	@Transactional
	public Response rentCar(RecordDto recordDto) {
		String carNumber = recordDto.getCarNumber();
		Long licenseId = recordDto.getLicenseId();
		LocalDate rentDate = recordDto.getRentDate();
		int rentDays = recordDto.getRentDays();
		Car car = carRepository.findById(carNumber).orElse(null);
		if (car == null || car.isFlRemoved() == true)
			return new Response(null, CAR_IS_NOT_EXISTS, goodCode, LocalDateTime.now().toString());
		if (car.isInUse() == true)
			return new Response(null, CAR_IN_USE, goodCode, LocalDateTime.now().toString());
		Driver driver = driverRepository.findById(licenseId).orElse(null);
		if (driver == null)
			return new Response(null, DRIVER_IS_NOT_EXISTS, goodCode, LocalDateTime.now().toString());
		Record record = new Record(driver, car, rentDate, rentDays);
		car.setInUse(true);
		recordRepository.save(record);
		return new Response(null, OK, goodCode, LocalDateTime.now().toString());
	}

	@Override
	@Transactional
	public Response returnCar(RecordDto recordDto) {
		String carNumber = recordDto.getCarNumber();
		LocalDate returnDate = recordDto.getReturnDate();
		int gasTankPercent = recordDto.getGasTankPercent();
		int damages = recordDto.getDamages();
		Record record = getRentRecord(carNumber);
		if (record == null)
			return new Response(null, CAR_NOT_RENTED, goodCode, LocalDateTime.now().toString());
		if (returnDate.isBefore(record.getRentDate()))
			return new Response(null, RETURN_DATE_WRONG, goodCode, LocalDateTime.now().toString());
		record.setDamages(damages);
		record.setGasTankPercent(gasTankPercent);
		record.setReturnDate(returnDate);

		Car car = carRepository.findById(carNumber).get();
		CarDto carDto = getCarDto(car);
		setCost(record, carDto);

		updateCarData(damages, car);
		recordRepository.save(record);
		return new Response(null, OK, goodCode, LocalDateTime.now().toString());
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
		Response response = getModel(carDto.getModelName());
		ModelDto modelDto = (ModelDto) response.getContent();
		if (modelDto == null)
			throw new RuntimeException("Information model inconsistency (the car exists but model doesn't)");
		float costGas = 0;
		costPeriod = getCostPeriod(record, period, modelDto);
		costGas = getCostGas(record, modelDto);
		record.setCost(costPeriod + costGas);

	}

	private ModelDto getModelDto(Model model) {
		return new ModelDto(model.getModelName(), model.getGasTank(), model.getCompany(), model.getCountry(),
				model.getPriceDay());
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
	public Response removeCar(String carNumber) {
		Car car = carRepository.findById(carNumber).orElse(null);
		if (car == null)
			return new Response(null, CAR_IS_NOT_EXISTS, goodCode, LocalDateTime.now().toString());
		if (car.isInUse())
			return new Response(null, CAR_IN_USE, goodCode, LocalDateTime.now().toString());
		car.setFlRemoved(true);
		carRepository.save(car);
		return new Response(null, OK, goodCode, LocalDateTime.now().toString());
	}

	@Override
	@Transactional
	public Response clear(DateDays dd) {

		List<Record> recordsForDelete = recordRepository.findByCarFlRemovedTrueOrReturnDateBefore(dd.date.minusDays(dd.days));
		recordsForDelete.forEach(recordRepository::delete);

		List<Car> carsForDelete = carRepository.findAll().stream().filter(x -> x.isFlRemoved() == true).collect(Collectors.toList());
		carsForDelete.forEach(carRepository::delete);
		List<CarDto> list = carsForDelete.stream().map(this::getCarDto).collect(Collectors.toList());
		return new Response(list, OK, goodCode, LocalDateTime.now().toString());
	}

	@Override
	@Transactional
	public Response getCarDrivers(String carNumber) {
		List<DriverDto> list = recordRepository.findAllByCarRegNumber(carNumber).stream().map(Record::getDriver)
				.distinct().map(this::getDriverDto).collect(Collectors.toList());
		return new Response(list, OK, goodCode, LocalDateTime.now().toString());

	}

	@Override
	@Transactional
	public Response getDriverCars(long licenseId) {
		List<CarDto> list = recordRepository.findAllByDriverLicenseId(licenseId).stream().map(Record::getCar).distinct()
				.map(this::getCarDto).collect(Collectors.toList());
		return new Response(list, OK, goodCode, LocalDateTime.now().toString());
	}

	@Override
	@Transactional
	public Response getAllModelNames() {
		List<String> list = modelRepository.getAllNames().collect(Collectors.toList());
		return new Response(list, OK, goodCode, LocalDateTime.now().toString());
	}

	@Override
	@Transactional
	public Response getAllCars() {
		List<CarDto> list = carRepository.findAllBy().map(this::getCarDto).collect(Collectors.toList());
		return new Response(list, OK, goodCode, LocalDateTime.now().toString());
	}

	@Override
	@Transactional
	public Response getAllDrivers() {
		List<DriverDto> list = driverRepository.findAllBy().map(this::getDriverDto).collect(Collectors.toList());
		return new Response(list, OK, goodCode, LocalDateTime.now().toString());
	}

	@Override
	@Transactional
	public Response getAllRecords() {
		List<RecordDto> list = recordRepository.findAllBy().map(this::getRecordDto).collect(Collectors.toList());
		return new Response(list, OK, goodCode, LocalDateTime.now().toString());
	}

	private RecordDto getRecordDto(Record record) {
		return new RecordDto(record.getDriver().getLicenseId(), record.getCar().getRegNumber(), record.getRentDate(),
				record.getReturnDate(), record.getGasTankPercent(), record.getRentDays(), record.getCost(),
				record.getDamages());

	}

	@Override
	public Response getMostPopularModelNames() {
		long countMax = recordRepository.getCountMaxForMostPopular();
		List<String> list = recordRepository.getListMostPopular(countMax);
		return new Response(list, OK, goodCode, LocalDateTime.now().toString());
	}

	@Override
	public Response getMostProfitModelNames() {
		Double profitMax = recordRepository.getMaxProfitFromModelNames();
		List<String> list = recordRepository.getListStringMostProfitModelNames(profitMax);
		return new Response(list, OK, goodCode, LocalDateTime.now().toString());
	}

	@Override
	public Response getModelProfit(String modelName) {
		Double value = recordRepository.getProfitFromModelName(modelName);
		return new Response(value, OK, goodCode, LocalDateTime.now().toString());
	}

}