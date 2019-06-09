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

    private String MODEL_IS_EXISTS = "model is exists";
    private String OK = "OK";
    private String CAR_IS_EXISTS = "car is exists";
    private String DRIVER_EXISTS = "driver exists";
    private String CAR_IN_USE = "car in use";
    private String CAR_NOT_RENTED = "car not rented";
    private String RETURN_DATE_WRONG = "return date wrong";
    private String CAR_IS_NOT_EXISTS = "car is not exists";
    private String MODEL_IS_NOT_EXISTS = "model is not exists";
    private String DRIVER_IS_NOT_EXISTS = "driver is not exists";
    private int goodCode = 200;
    private String currentDate = LocalDateTime.now().toString();
    private Response response = null;

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
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        String modelName = modelDto.getModelName();
        if (modelRepository.existsById(modelName)) {
            return response.setMessage(MODEL_IS_EXISTS);
        }
        Model model = new Model().setModelName(modelDto.getModelName()).setGasTank(modelDto.getGasTank())
                .setCompany(modelDto.getCompany()).setCountry(modelDto.getCountry()).setPriceDay(modelDto.getPriceDay());
        modelRepository.save(model);
        return response.setMessage(OK);
    }

    @Override
    @Transactional
    public Response addCar(CarDto carDto) {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        String regNumber = carDto.getRegNumber();
        if (carRepository.existsById(regNumber))
            return response.setMessage(CAR_IS_EXISTS);
        String modelName = carDto.getModelName();
        Model model = modelRepository.findById(modelName).orElse(null);
        if (model == null)
            return response.setMessage(MODEL_IS_NOT_EXISTS);

        Car car = new Car().setRegNumber(carDto.getRegNumber()).setColor(carDto.getColor())
                .setState(carDto.getState()).setInUse(carDto.isInUse()).setFlRemoved(carDto.isFlRemoved())
                .setModel(model);
        carRepository.save(car);
        return response.setMessage(OK);
    }

    @Override
    @Transactional
    public Response addDriver(DriverDto driverDto) {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        Long licenseId = driverDto.getLicenseId();
        if (driverRepository.existsById(licenseId))
            return response.setMessage(DRIVER_EXISTS);
        Driver driver = new Driver().setLicenseId(licenseId).setName(driverDto.getName())
                .setBirthYear(driverDto.getBirthYear()).setPhone(driverDto.getPhone());
        driverRepository.save(driver);
        return response.setMessage(OK);
    }

    @Override
    public Response getModel(String modelName) {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        Model model = modelRepository.findById(modelName).orElse(null);
        if (model == null)
            return response.setMessage(MODEL_IS_NOT_EXISTS);
        return response.setMessage(OK).setContent(getModelDto(model));
    }

    @Override
    public Response getCar(String carNumber) {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        Car car = carRepository.findById(carNumber).orElse(null);
        if (car == null)
            return response.setMessage(CAR_IS_NOT_EXISTS);
        return response.setMessage(OK).setContent(getCarDto(car));
    }

    @Override
    public Response getDriver(long licenseId) {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        Driver driver = driverRepository.findById(licenseId).orElse(null);
        if (driver == null)
            return response.setMessage(DRIVER_IS_NOT_EXISTS);
        return response.setMessage(OK).setContent(getDriverDto(driver));
    }


    // **************************************************************************************************
    @Override
    @Transactional
    public Response rentCar(RecordDto recordDto) {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        String regNumber = recordDto.getRegNumber();
        Long licenseId = recordDto.getLicenseId();
        LocalDate rentDate = recordDto.getRentDate();
        int rentDays = recordDto.getRentDays();
        Car car = carRepository.findById(regNumber).orElse(null);
        if (car == null || car.isFlRemoved() == true)
            return response.setMessage(CAR_IS_NOT_EXISTS);
        if (car.isInUse() == true)
            return response.setMessage(CAR_IN_USE);
        Driver driver = driverRepository.findById(licenseId).orElse(null);
        if (driver == null)
            return response.setMessage(DRIVER_IS_NOT_EXISTS);
        Record record = new Record().setDriver(driver).setCar(car).setRentDate(rentDate).setRentDays(rentDays);
        car.setInUse(true);
        recordRepository.save(record);
        return response.setMessage(OK);
    }

    // ***************************************************************************************************
    @Override
    @Transactional
    public Response returnCar(RecordDto recordDto) {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        String regNumber = recordDto.getRegNumber();
        LocalDate returnDate = recordDto.getReturnDate();
        int gasTankPercent = recordDto.getGasTankPercent();
        int damages = recordDto.getDamages();
        Record record = getRentRecord(regNumber);
        if (record == null)
            return response.setMessage(CAR_NOT_RENTED);
        if (returnDate.isBefore(record.getRentDate()))
            return response.setMessage(RETURN_DATE_WRONG);
        record.setDamages(damages).setGasTankPercent(gasTankPercent).setReturnDate(returnDate);
        Car car = carRepository.findById(regNumber).get();
        CarDto carDto = getCarDto(car);
        setCost(record, carDto);
        updateCarData(damages, car);
        recordRepository.save(record);
        return response.setMessage(OK);
    }

    private Record getRentRecord(String regNumber) {
        return recordRepository.findAll().stream()
                .filter(r -> r.getCar().getRegNumber().equals(regNumber) && r.getReturnDate() == null).findFirst()
                .orElse(null);
    }

    // *****************************************************************************************************************
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
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        Car car = carRepository.findById(carNumber).orElse(null);
        if (car == null)
            return response.setMessage(CAR_IS_NOT_EXISTS);
        if (car.isInUse())
            return response.setMessage(CAR_IN_USE);
        car.setFlRemoved(true);
        carRepository.save(car);
        return response.setMessage(OK);
    }

    @Override
    @Transactional
    public Response clear(DateDays dd) {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        List<Record> recordsForDelete = recordRepository.findByCarFlRemovedTrueOrReturnDateBefore(dd.date.minusDays(dd.days));
        recordsForDelete.forEach(recordRepository::delete);
        List<Car> carsForDelete = carRepository.findAll().stream().filter(x -> x.isFlRemoved() == true).collect(Collectors.toList());
        carsForDelete.forEach(carRepository::delete);
        List<CarDto> list = carsForDelete.stream().map(this::getCarDto).collect(Collectors.toList());
        return response.setMessage(OK).setContent(list);
    }

    @Override
    @Transactional
    public Response getCarDrivers(String carNumber) {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        List<DriverDto> list = recordRepository.findAllByCarRegNumber(carNumber).stream().map(Record::getDriver)
                .distinct().map(this::getDriverDto).collect(Collectors.toList());
        return response.setMessage(OK).setContent(list);
    }

    @Override
    @Transactional
    public Response getDriverCars(long licenseId) {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        List<CarDto> list = recordRepository.findAllByDriverLicenseId(licenseId).stream().map(Record::getCar).distinct()
                .map(this::getCarDto).collect(Collectors.toList());
        return response.setMessage(OK).setContent(list);
    }

    // *****************************************************************************************************************
    @Override
    @Transactional
    public Response getAllModelNames() {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        List<String> list = modelRepository.getAllNames().collect(Collectors.toList());
        return response.setMessage(OK).setContent(list);
    }

    @Override
    @Transactional
    public Response getAllModels() {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        List<ModelDto> list = modelRepository.findAllBy().map(this::getModelDto).collect(Collectors.toList());
        return response.setMessage(OK).setContent(list);
    }


    @Override
    @Transactional
    public Response getAllCars() {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        List<CarDto> list = carRepository.findAllBy().map(this::getCarDto).collect(Collectors.toList());
        return response.setMessage(OK).setContent(list);
    }

    @Override
    @Transactional
    public Response getAllDrivers() {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        List<DriverDto> list = driverRepository.findAllBy().map(this::getDriverDto).collect(Collectors.toList());
        return response.setMessage(OK).setContent(list);
    }

    @Override
    @Transactional
    public Response getAllRecords() {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        List<RecordDto> list = recordRepository.findAllBy().map(this::getRecordDto).collect(Collectors.toList());
        return response.setMessage(OK).setContent(list);
    }

    // *****************************************************************************************************************
    @Override
    @Transactional
    public Response getMostPopularModels() {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        long countMax = recordRepository.getCountMaxForMostPopular();
        List<String> list = recordRepository.getListMostPopular(countMax);
        List<ModelDto> listModels = modelRepository.findAllBy()
                .filter(x -> list.contains(x.getModelName())).map(this::getModelDto).collect(Collectors.toList());
        return response.setMessage(OK).setContent(listModels);

    }

    @Override
    @Transactional
    public Response getMostProfitModels() {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        Double profitMax = recordRepository.getMaxProfitFromModelNames();
        List<String> list = recordRepository.getListStringMostProfitModelNames(profitMax);
        List<ModelDto> listModels = modelRepository.findAllBy()
                .filter(x -> list.contains(x.getModelName())).map(this::getModelDto).collect(Collectors.toList());
        return response.setMessage(OK).setContent(listModels);
    }

    @Override
    public Response getModelProfit(String modelName) {
        response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        Double value = recordRepository.getProfitFromModelName(modelName);
        return response.setMessage(OK).setContent(value);
    }

    //********************helping methods********************************
    private ModelDto getModelDto(Model model) {
        return new ModelDto().setModelName(model.getModelName()).setGasTank(model.getGasTank())
                .setCompany(model.getCompany()).setCountry(model.getCountry()).setPriceDay(model.getPriceDay());
    }

    private CarDto getCarDto(Car car) {
        return new CarDto().setRegNumber(car.getRegNumber()).setColor(car.getColor())
                .setModelName(car.getModel().getModelName());
    }

    private DriverDto getDriverDto(Driver driver) {
        return new DriverDto().setLicenseId(driver.getLicenseId()).setName(driver.getName())
                .setBirthYear(driver.getBirthYear()).setPhone(driver.getPhone());
    }

    private RecordDto getRecordDto(Record record) {
        return new RecordDto().setLicenseId(record.getDriver().getLicenseId()).setRegNumber(record.getCar().getRegNumber())
                .setRentDate(record.getRentDate()).setReturnDate(record.getReturnDate())
                .setGasTankPercent(record.getGasTankPercent()).setRentDays(record.getRentDays()).setCost(record.getCost())
                .setDamages(record.getDamages());
    }

}