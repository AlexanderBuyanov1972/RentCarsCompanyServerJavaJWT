package cars.controller;

import cars.dto.CarDto;
import cars.dto.DriverDto;
import cars.dto.ModelDto;
import cars.dto.RecordDto;
import cars.controller.constants.CarsApi;
import cars.service_rent_company.IRentCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class CarsController {

    @Autowired
    IRentCompany company;

    @PostMapping(value = CarsApi.MODEL)
    ResponseEntity<?> addModel(@RequestBody ModelDto carModel) {
        return company.addModel(carModel);
    }

    @PostMapping(value = CarsApi.CAR)
    ResponseEntity<?> addCar(@RequestBody CarDto carDto) {
        return company.addCar(carDto);
    }

    @PostMapping(value = CarsApi.DRIVER)
    ResponseEntity<?> addDriver(@RequestBody DriverDto driverDto) {
        return company.addDriver(driverDto);
    }

    @GetMapping(value = CarsApi.MODEL + "/{modelName}")
    ResponseEntity<?> getModel(@PathVariable(value = "modelName") String modelName) {
        return company.getModel(modelName);
    }

    @RequestMapping(value = CarsApi.CAR + "/{carNumber}")
    ResponseEntity<?> getCar(@PathVariable(value = "carNumber") String carNumber) {
        return company.getCar(carNumber);
    }

    @RequestMapping(value = CarsApi.DRIVER + "/{licenseId}")
    ResponseEntity<?> getDriver(@PathVariable(value = "licenseId") long licenseId) {
        return company.getDriver(licenseId);
    }

    @GetMapping(value = CarsApi.GET_ALL_MODEL_NAMES)
    ResponseEntity<?> getAllModelNames() {
        return company.getAllModelNames();
    }

    @GetMapping(value = CarsApi.GET_ALL_MODELS)
    ResponseEntity<?> getAllModels() {
        return company.getAllModels();
    }

    @GetMapping(value = CarsApi.GET_ALL_CARS)
    ResponseEntity<?> getAllCars() {
        return company.getAllCars();
    }

    @GetMapping(value = CarsApi.GET_ALL_DRIVERS)
    ResponseEntity<?> getAllDrivers() {
        return company.getAllDrivers();
    }

    @GetMapping(value = CarsApi.GET_ALL_RECORDS)
    ResponseEntity<?> getAllRecords() {
        return company.getAllRecords();
    }

    @GetMapping(value = CarsApi.GET_DRIVER_CARS + "/{licenseId}")
    ResponseEntity<?> getDriverCars(@PathVariable(value = "licenseId") long licenseId) {
        return company.getDriverCars(licenseId);
    }

    @GetMapping(value = CarsApi.GET_CAR_DRIVERS + "/{carNumber}")
    ResponseEntity<?> getCarDrivers(@PathVariable(value = "carNumber") String carNumber) {
        return company.getCarDrivers(carNumber);
    }

    @GetMapping(value = CarsApi.MOST_PROFIT_MODELS)
    ResponseEntity<?> getMostProfitModels() {
        return company.getMostProfitModels();
    }

    @GetMapping(value = CarsApi.GET_PROFIT_MODEL + "/{modelName}")
    ResponseEntity<?> getModelProfit(@PathVariable(value = "modelName") String modelName) {
        return company.getModelProfit(modelName);
    }

    @GetMapping(value = CarsApi.MOST_POPULAR_MODELS)
    ResponseEntity<?> getMostPopularModels() {
        return company.getMostPopularModels();
    }

    @PostMapping(value = CarsApi.CAR_RENT)
    ResponseEntity<?> rentCar(@RequestBody RecordDto record) {
        return company.rentCar(record);
    }

    @PostMapping(value = CarsApi.CAR_RETURN)
    ResponseEntity<?> returnCar(@RequestBody RecordDto record) {
        return company.returnCar(record);
    }

    @DeleteMapping(value = CarsApi.REMOVE_CAR + "/{carNumber}")
    ResponseEntity<?> removeCar(@PathVariable(value = "carNumber") String carNumber) {
        return company.removeCar(carNumber);
    }

    @GetMapping(value = CarsApi.CLEAR_CARS + "/{days}")
    ResponseEntity<?> clearCars(@PathVariable(value = "days") int days) {
        return company.clear(days);
    }


}