package cars.controller;

import cars.dto.*;
import cars.dto.constants.CarsApiConstants;
import cars.service.main.IRentCompany;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping()
@Api(value = "onlineStore")
public class CarsController {
    @Autowired
    IRentCompany company;

    // -----------addModel----------------------------------------------------------------------------------------------
    @ApiOperation(value = "Adding a new model to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added model"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = CarsApiConstants.ADD_MODEL)
    Response addModel(@RequestBody ModelDto carModel) {
        return company.addModel(carModel);
    }

    // -----------addCar------------------------------------------------------------------------------------------------
    @ApiOperation(value = "Adding a new car to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added car"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = CarsApiConstants.ADD_CAR)
    Response addCar(@RequestBody CarDto carDto) {
        return company.addCar(carDto);
    }

    // -----------addDriver---------------------------------------------------------------------------------------------
    @ApiOperation(value = "Adding a new driver to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added driver"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = CarsApiConstants.ADD_DRIVER)
    Response addDriver(@RequestBody DriverDto driverDto) {
        return company.addDriver(driverDto);
    }

    // -----------getModel----------------------------------------------------------------------------------------------
    @ApiOperation(value = "Getting a new model to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully gotten model"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CarsApiConstants.GET_MODEL + "/{modelName}")
    Response getModel(@PathVariable(value = "modelName") String modelName) {
        return company.getModel(modelName);
    }

    // -------------getCar----------------------------------------------------------------------------------------------
    @ApiOperation(value = "Getting a new car to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully gotten car"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = CarsApiConstants.GET_CAR + "/{carNumber}")
    Response getCar(@PathVariable(value = "carNumber") String carNumber) {
        return company.getCar(carNumber);
    }

    // --------------getDriver------------------------------------------------------------------------------------------
    @ApiOperation(value = "Getting a new driver to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully gotten driver"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = CarsApiConstants.GET_DRIVER + "/{licenseId}")
    Response getDriver(@PathVariable(value = "licenseId") long licenseId) {
        return company.getDriver(licenseId);
    }

    // ---------------rentCar-------------------------------------------------------------------------------------------
    @ApiOperation(value = "Adding a new record to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully opened record"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = CarsApiConstants.RENT_CAR)
    Response rentCar(@RequestBody RecordDto record) {
        return company.rentCar(record);
    }

    // ---------------returnCar-----------------------------------------------------------------------------------------
    @ApiOperation(value = "Finishing a open record to DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully finished record"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = CarsApiConstants.RETURN_CAR)
    Response returnCar(@RequestBody RecordDto record) {
        return company.returnCar(record);
    }

    // ---------------removeCar-----------------------------------------------------------------------------------------
    @ApiOperation(value = "Removing a car from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully removed car"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = CarsApiConstants.REMOVE_CAR + "/{carNumber}")
    Response removeCar(@PathVariable(value = "carNumber") String carNumber) {
        return company.removeCar(carNumber);
    }

    // ---------------clearCar------------------------------------------------------------------------------------------
    @ApiOperation(value = "Removing records and cars from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully removed records and cars"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CarsApiConstants.CLEAR_CARS + "/{date}" + "/{days}")
    Response clearCars(@PathVariable(value = "date") String date,
                       @PathVariable(value = "days") int days) {
        return company.clear(date, days);
    }

    // ---------------getCarDrivers-------------------------------------------------------------------------------------
    @ApiOperation(value = "Getting car drivers from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully gotten car drivers"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CarsApiConstants.GET_CAR_DRIVERS + "/{carNumber}")
    Response getCarDrivers(@PathVariable(value = "carNumber") String carNumber) {
        return company.getCarDrivers(carNumber);
    }

    // ----------------getDriverCars------------------------------------------------------------------------------------
    @ApiOperation(value = "Getting a driver cars from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully gotten driver cars"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CarsApiConstants.GET_DRIVER_CARS + "/{licenseId}")
    Response getDriverCars(@PathVariable(value = "licenseId") long licenseId) {
        return company.getDriverCars(licenseId);
    }

    // ----------------getAllModelNames-------------------------------------------------------------------------------------
    @ApiOperation(value = "Getting all models from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully gotten all models"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CarsApiConstants.GET_ALL_MODEL_NAMES)
    Response getAllModelNames() {
        return company.getAllModelNames();
    }
    // ----------------getAllModels-------------------------------------------------------------------------------------
    @ApiOperation(value = "Getting all models from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully gotten all models"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CarsApiConstants.GET_ALL_MODELS)
    Response getAllModels() {
        return company.getAllModels();
    }

    // ----------------getAllCars---------------------------------------------------------------------------------------
    @ApiOperation(value = "Getting all cars from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully gotten all cars"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CarsApiConstants.GET_ALL_CARS)
    Response getAllCars() {
        return company.getAllCars();
    }

    // ----------------getAllDrivers------------------------------------------------------------------------------------
    @ApiOperation(value = "Getting all drivers from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully gotten all drivers"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CarsApiConstants.GET_ALL_DRIVERS)
    Response getAllDrivers() {
        return company.getAllDrivers();
    }

    // ----------------getAllRecords------------------------------------------------------------------------------------
    @ApiOperation(value = "Getting all records from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully gotten all records"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CarsApiConstants.GET_ALL_RECORDS)
    Response getAllRecords() {
        return company.getAllRecords();
    }

    // ----------------getMostPopularModels-----------------------------------------------------------------------------
    @ApiOperation(value = "Getting most popular models from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully gotten most popular models"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CarsApiConstants.MOST_POPULAR_MODELS)
    Response getMostPopularModels() {
        return company.getMostPopularModels();
    }

    // -----------------getMostProfitModels-----------------------------------------------------------------------------
    @ApiOperation(value = "Getting most profit models from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully gotten most profit models"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CarsApiConstants.MOST_PROFIT_MODELS)
    Response getMostProfitModels() {
        return company.getMostProfitModels();
    }

    // -----------------getModelProfit----------------------------------------------------------------------------------
    @ApiOperation(value = "Getting profit of  model from DataBase", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully gotten profit of model"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = CarsApiConstants.GET_PROFIT_MODEL + "/{modelName}")
    Response getModelProfit(@PathVariable(value = "modelName") String modelName) {
        return company.getModelProfit(modelName);
    }
    // -----------------------------------------------------------------------------------------------------------------
}