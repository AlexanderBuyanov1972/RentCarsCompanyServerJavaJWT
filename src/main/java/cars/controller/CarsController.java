package cars.controller;

import cars.dto.accounting.AccountDto;
import cars.dto.main.*;
import cars.service.accounting.IAccountsManagment;
import cars.service.main.IRentCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CarsController {
	@Autowired
	IRentCompany company;

//	@Autowired
//	IAccountsManagment accounts;
//
//	// ******************Accounting*********************************
//	// ------------------addAccount-------------------------------
//	@PostMapping(value = "/account")
//	boolean addAccount(@RequestBody AccountDto accountDto) {
//		return accounts.addAccount(accountDto.getUsername(), accountDto.getPassword(), accountDto.getRoles());
//	}
//
//	// ------------------removeAccount------------------------------
//	@DeleteMapping(value = "/account/{username}")
//	boolean removeAccount(@PathVariable(value = "username") String username) {
//		return accounts.removeAccount(username);
//	}
//
//	// ------------------updatePassword------------------------------
//	@PutMapping(value = "/account")
//	boolean updatePassword(@RequestBody AccountDto accountDto) {
//		return accounts.updatePassword(accountDto.getUsername(), accountDto.getPassword());
//	}
//
//	// -------------------addRole------------------------------------
//	@PostMapping(value = "/role")
//	boolean addRole(@RequestBody AccountDto accountDto) {
//		return accounts.addRole(accountDto.getUsername(), accountDto.getRoles());
//
//	}
//
//	// -------------------removeRole---------------------------------
//	@DeleteMapping(value = "/role")
//	boolean removeRole(@RequestBody AccountDto accountDto) {
//		return accounts.removeRole(accountDto.getUsername(), accountDto.getRoles());
//
//	}

	// ************RentCompany*********************************
	// -----------addModel------------------------------------
//	@ApiOperation(value = "Adding a new quartes to DataBase", response = Response.class)
//	@ApiResponses(value = {
//	@ApiResponse(code = 200, message = "Successfully added quartes"),
//	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	@CrossOrigin
	@PostMapping(value = CarsApiConstants.ADD_MODEL)
	Response addModel(@RequestBody ModelDto carModel) {
		return company.addModel(carModel);
	}

	// -----------addCar--------------------------------------

	@PostMapping(value = CarsApiConstants.ADD_CAR)
	Response addCar(@RequestBody CarDto carDto) {
		return company.addCar(carDto);
	}
	// -----------addDriver-----------------------------------

	@PostMapping(value = CarsApiConstants.ADD_DRIVER)
	Response addDriver(@RequestBody DriverDto driverDto) {
		return company.addDriver(driverDto);
	}

	// -----------getModel------------------------------------
	@GetMapping(value = CarsApiConstants.GET_MODEL + "/{modelName}")
	Response getModel(@PathVariable(value = "modelName") String modelName) {
		return company.getModel(modelName);
	}

	// -------------getCar-------------------------------------
	@RequestMapping(value = CarsApiConstants.GET_CAR + "/{carNumber}")
	Response getCar(@PathVariable(value = "carNumber") String carNumber) {
		return company.getCar(carNumber);
	}

	// --------------getDriver---------------------------------
	@RequestMapping(value = CarsApiConstants.GET_DRIVER + "/{licenseId}")
	Response getDriver(@PathVariable(value = "licenseId") long licenseId) {
		return company.getDriver(licenseId);
	}

	// ---------------rentCar---------------------------------
	@PostMapping(value = CarsApiConstants.RENT_CAR)
	Response rentCar(@RequestBody RecordDto record) {
		return company.rentCar(record);
	}

	// ---------------returnCar-------------------------------
	@PostMapping(value = CarsApiConstants.RETURN_CAR)
	Response returnCar(@RequestBody RecordDto record) {
		return company.returnCar(record);
	}

	// ---------------removeCar-------------------------------
	@DeleteMapping(value = CarsApiConstants.REMOVE_CAR + "/{carNumber}")
	Response removeCar(@PathVariable(value = "carNumber") String carNumber) {
		return company.removeCar(carNumber);
	}

	// ---------------clearCar--------------------------------
	@PostMapping(value = CarsApiConstants.CLEAR_CARS)
	Response clearCars(@RequestBody DateDays dd) {
		return company.clear(dd);
	}

	// ---------------getCarDrivers---------------------------
	@GetMapping(value = CarsApiConstants.GET_CAR_DRIVERS + "/{carNumber}")
	Response getCarDrivers(@PathVariable(value = "carNumber") String carNumber) {
		return company.getCarDrivers(carNumber);
	}

	// ----------------getDriverCars--------------------------
	@GetMapping(value = CarsApiConstants.GET_DRIVER_CARS + "/{licenseId}")
	Response getDriverCars(@PathVariable(value = "licenseId") long licenseId) {
		return company.getDriverCars(licenseId);
	}

	// ----------------getAllModels---------------------------
	@GetMapping(value = CarsApiConstants.GET_ALL_MODELS)
	Response getAllModels() {
		return company.getAllModelNames();
	}

	// ----------------getAllCars-----------------------------
	@GetMapping(value = CarsApiConstants.GET_ALL_CARS)
	Response getAllCars() {
		return company.getAllCars();
	}

	// ----------------getAllDrivers--------------------------
	@GetMapping(value = CarsApiConstants.GET_ALL_DRIVERS)
	Response getAllDrivers() {
		return company.getAllDrivers();
	}

	// ----------------getAllRecords--------------------------
	@GetMapping(value = CarsApiConstants.GET_ALL_RECORDS)
	Response getAllRecords() {
		return company.getAllRecords();
	}

	// ----------------getMostPopularModels-------------------
	@GetMapping(value = CarsApiConstants.MOST_POPULAR_MODELS)
	Response getMostPopularModels() {
		return company.getMostPopularModelNames();
	}

	// -----------------getMostProfitModels------------------
	@GetMapping(value = CarsApiConstants.MOST_PROFIT_MODELS)
	Response getMostProfitModels() {
		return company.getMostProfitModelNames();
	}

	// -----------------getModelProfit---------------------------
	@GetMapping(value = CarsApiConstants.GET_PROFIT_MODEL + "/{modelName}")
	Response getModelProfit(@PathVariable(value = "modelName") String modelName) {
		return company.getModelProfit(modelName);
	}
	// -----------------------------------------------------------
}