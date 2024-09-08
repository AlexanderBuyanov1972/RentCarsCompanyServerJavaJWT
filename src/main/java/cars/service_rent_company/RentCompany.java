package cars.service_rent_company;

import cars.controller.messages.CarsMessages;
import cars.dto.CarDto;
import cars.dto.DriverDto;
import cars.dto.ModelDto;
import cars.dto.RecordDto;
import cars.dao.CarRepository;
import cars.dao.DriverRepository;
import cars.dao.ModelRepository;
import cars.dao.RecordRepository;
import cars.entities.Car;
import cars.entities.Driver;
import cars.entities.Model;
import cars.entities.Record;
import cars.enums.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    CarRepository carRepository;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    ModelRepository modelRepository;
    @Autowired
    RecordRepository recordRepository;

    // *****************
    @Override
    @Transactional
    public ResponseEntity<?> addModel(ModelDto modelDto) {
        if (modelRepository.existsById(modelDto.getModelName()))
            return ResponseEntity.badRequest().body(CarsMessages.MODEL_IS_EXISTS);
        return ResponseEntity.ok(modelRepository.save(new Model(modelDto)));
    }

    @Override
    @Transactional
    public ResponseEntity<?> addCar(CarDto carDto) {
        String regNumber = carDto.getRegNumber();
        if (carRepository.existsById(regNumber))
            return ResponseEntity.badRequest().body(CarsMessages.CAR_IS_EXISTS);
        String modelName = carDto.getModelName();
        Model model = modelRepository.findById(modelName).orElse(null);
        if (model == null)
            return ResponseEntity.badRequest().body(CarsMessages.MODEL_IS_NOT_EXISTS);
        return ResponseEntity.ok(carRepository.save(new Car(carDto, model)));
    }

    @Override
    @Transactional
    public ResponseEntity<?> addDriver(DriverDto driverDto) {
        if (driverRepository.existsById(driverDto.getLicenseId()))
            return ResponseEntity.badRequest().body(CarsMessages.DRIVER_EXISTS);
        return ResponseEntity.ok(driverRepository.save(new Driver(driverDto)));
    }

    @Override
    public ResponseEntity<?> getModel(String modelName) {
        Model model = modelRepository.findById(modelName).orElse(null);
        if (model == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ModelDto(model));
    }

    @Override
    public ResponseEntity<?> getCar(String carNumber) {
        Car car = carRepository.findById(carNumber).orElse(null);
        if (car == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new CarDto(car));
    }

    @Override
    public ResponseEntity<?> getDriver(long licenseId) {
        Driver driver = driverRepository.findById(licenseId).orElse(null);
        if (driver == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new DriverDto(driver));
    }

    @Override
    @Transactional
    public ResponseEntity<?> rentCar(RecordDto recordDto) {
        String regNumber = recordDto.getRegNumber();
        Long licenseId = recordDto.getLicenseId();
        LocalDate rentDate = recordDto.getRentDate();
        int rentDays = recordDto.getRentDays();
        Car car = carRepository.findByRegNumber(regNumber);
        if (car == null || car.isFlRemoved() == true)
            return ResponseEntity.badRequest().body(CarsMessages.CAR_IS_NOT_EXISTS);
        if (car.isInUse())
            return ResponseEntity.badRequest().body(CarsMessages.CAR_IN_USE);
        Driver driver = driverRepository.findById(licenseId).orElse(null);
        if (driver == null)
            return ResponseEntity.badRequest().body(CarsMessages.DRIVER_IS_NOT_EXISTS);
        car.setInUse(true);
        carRepository.save(car);
        return ResponseEntity.ok(recordRepository.save(new Record(driver, car, rentDate, rentDays)));
    }

    @Override
    @Transactional
    public ResponseEntity<?> returnCar(RecordDto recordDto) {
        String regNumber = recordDto.getRegNumber();
        LocalDate returnDate = recordDto.getReturnDate();
        int gasTankPercent = recordDto.getGasTankPercent();
        int damages = recordDto.getDamages();
        Record record = getRentRecord(regNumber);
        if (record == null)
            return ResponseEntity.badRequest().body(CarsMessages.CAR_NOT_RENTED);
        if (returnDate.isBefore(record.getRentDate()))
            return ResponseEntity.badRequest().body(CarsMessages.RETURN_DATE_WRONG);
        record.setDamages(damages);
        record.setGasTankPercent(gasTankPercent);
        record.setReturnDate(returnDate);
        Car car = carRepository.findById(regNumber).get();
        setCost(record, new CarDto(car));
        updateCarData(damages, car);
        return ResponseEntity.ok(recordRepository.save(record));
    }

    private Record getRentRecord(String regNumber) {
        return recordRepository.findAll().stream()
                .filter(r -> r.getCar().getRegNumber().equals(regNumber) && r.getReturnDate() == null).findFirst()
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
        ModelDto modelDto = (ModelDto) getModel(carDto.getModelName()).getBody();
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
    public ResponseEntity<?> removeCar(String carNumber) {
        Car car = carRepository.findById(carNumber).orElse(null);
        if (car == null)
            return ResponseEntity.badRequest().body(CarsMessages.CAR_IS_NOT_EXISTS);
        if (car.isInUse())
            return ResponseEntity.badRequest().body(CarsMessages.CAR_IN_USE);
        car.setFlRemoved(true);
        carRepository.save(car);
        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public ResponseEntity<?> clear(int days) {
        LocalDate ld = LocalDate.now();
        List<Record> recordsForDelete = recordRepository.findByCarFlRemovedTrueOrReturnDateBefore(ld.minusDays(days));
        recordsForDelete.forEach(recordRepository::delete);

        List<Car> carsForDelete = carRepository.findAll().stream().filter(Car::isFlRemoved).toList();
        carsForDelete.forEach(carRepository::delete);

        List<CarDto> list = carsForDelete.stream().map(CarDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // ********************* getCarDrivers, getDriverCars **************************************************************
    @Override
    @Transactional
    public ResponseEntity<?> getCarDrivers(String carNumber) {
        List<DriverDto> list = recordRepository.findAllByCarRegNumber(carNumber).stream().map(Record::getDriver)
                .distinct().map(DriverDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @Override
    @Transactional
    public ResponseEntity<?> getDriverCars(long licenseId) {
        List<CarDto> list = recordRepository.findAllByDriverLicenseId(licenseId).stream().map(Record::getCar).distinct()
                .map(CarDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // ************* getAllModelNames, getAllModels, getAllCars, getAllDrivers, getAllRecords **************************
    @Override
    @Transactional
    public ResponseEntity<?> getAllModelNames() {
        List<String> list = modelRepository.getAllNames().collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @Override
    @Transactional
    public ResponseEntity<?> getAllModels() {
        List<ModelDto> list = modelRepository.findAllBy().map(ModelDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }


    @Override
    @Transactional
    public ResponseEntity<?> getAllCars() {
        List<CarDto> list = carRepository.findAllBy().filter(el -> !el.isFlRemoved()).map(CarDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @Override
    @Transactional
    public ResponseEntity<?> getAllDrivers() {
        List<DriverDto> list = driverRepository.findAllBy().map(DriverDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @Override
    @Transactional
    public ResponseEntity<?> getAllRecords() {
        List<RecordDto> list = recordRepository.findAllBy().map(RecordDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    // ********************* getMostPopularModels, getMostProfitModels, getModelProfit *********************************
    @Override
    @Transactional
    public ResponseEntity<?> getMostPopularModels() {
        long countMax = recordRepository.getCountMaxForMostPopular();
        List<String> list = recordRepository.getListMostPopular(countMax);
        List<ModelDto> listModels = modelRepository.findAllBy()
                .filter(x -> list.contains(x.getModelName())).map(ModelDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(listModels);

    }

    @Override
    @Transactional
    public ResponseEntity<?> getMostProfitModels() {
        Double profitMax = recordRepository.getMaxProfitFromModelNames();
        List<String> list = recordRepository.getListStringMostProfitModelNames(profitMax);
        List<ModelDto> listModels = modelRepository.findAllBy()
                .filter(x -> list.contains(x.getModelName())).map(ModelDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(listModels);
    }

    @Override
    public ResponseEntity<?> getModelProfit(String modelName) {
        Double value = recordRepository.getProfitFromModelName(modelName);
        if (value == null)
            return ResponseEntity.ok(0);
        return ResponseEntity.ok(value);
    }
}