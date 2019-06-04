package cars.controller;

import cars.dto.accounting.AccountDto;
import cars.dto.main.*;
import cars.service.accounting.IAccountsManagment;
import cars.service.main.IRentCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarsController {
	@Autowired
	IRentCompany company;

	@Autowired
	IAccountsManagment accounts;

	// ******************Accounting*********************************
	// ------------------addAccount-------------------------------
	@PostMapping(value = "/account")
	boolean addAccount(@RequestBody AccountDto accountDto) {
		return accounts.addAccount(accountDto.getUsername(), accountDto.getPassword(), accountDto.getRoles());
	}

	// ------------------removeAccount------------------------------
	@DeleteMapping(value = "/account/{username}")
	boolean removeAccount(@PathVariable(value = "username") String username) {
		return accounts.removeAccount(username);
	}

	// ------------------updatePassword------------------------------
	@PutMapping(value = "/account")
	boolean updatePassword(@RequestBody AccountDto accountDto) {
		return accounts.updatePassword(accountDto.getUsername(), accountDto.getPassword());
	}

	// -------------------addRole------------------------------------
	@PostMapping(value = "/role")
	boolean addRole(@RequestBody AccountDto accountDto) {
		return accounts.addRole(accountDto.getUsername(), accountDto.getRoles());

	}

	// -------------------removeRole---------------------------------
	@DeleteMapping(value = "/role")
	boolean removeRole(@RequestBody AccountDto accountDto) {
		return accounts.removeRole(accountDto.getUsername(), accountDto.getRoles());

	}

	// ************RentCompany*********************************
	// -----------addModel------------------------------------
	@PostMapping(value = CarsApiConstants.ADD_MODEL)
	CarsReturnCode addModel(@RequestBody ModelDto carModel) {
		return company.addModel(carModel);
	}

	// -----------addCar--------------------------------------
	@PostMapping(value = CarsApiConstants.ADD_CAR)
	CarsReturnCode addCar(@RequestBody CarDto carDto) {
		return company.addCar(carDto);
	}
	// -----------addDriver-----------------------------------

	@PostMapping(value = CarsApiConstants.ADD_DRIVER)
	CarsReturnCode addDriver(@RequestBody DriverDto driverDto) {
		return company.addDriver(driverDto);
	}

	// -----------getModel------------------------------------
	@GetMapping(value = CarsApiConstants.GET_MODEL + "/{modelName}")
	ModelDto getModel(@PathVariable(value = "modelName") String modelName) {
		return company.getModel(modelName);
	}

	// -------------getCar-------------------------------------
	@RequestMapping(value = CarsApiConstants.GET_CAR + "/{carNumber}")
	CarDto getCar(@PathVariable(value = "carNumber") String carNumber) {
		return company.getCar(carNumber);
	}

	// --------------getDriver---------------------------------
	@RequestMapping(value = CarsApiConstants.GET_DRIVER + "/{licenseId}")
	DriverDto getDriver(@PathVariable(value = "licenseId") long licenseId) {
		return company.getDriver(licenseId);
	}

	// ---------------rentCar---------------------------------
	@PostMapping(value = CarsApiConstants.RENT_CAR)
	CarsReturnCode rentCar(@RequestBody RecordDto record) {
		return company.rentCar(record);
	}

	// ---------------returnCar-------------------------------
	@PostMapping(value = CarsApiConstants.RETURN_CAR)
	CarsReturnCode returnCar(@RequestBody RecordDto record) {
		return company.returnCar(record);
	}

	// ---------------removeCar-------------------------------
	@DeleteMapping(value = CarsApiConstants.REMOVE_CAR + "/{carNumber}")
	CarsReturnCode removeCar(@PathVariable(value = "carNumber") String carNumber) {
		return company.removeCar(carNumber);
	}

	// ---------------clearCar--------------------------------
	@PostMapping(value = CarsApiConstants.CLEAR_CARS)
	List<CarDto> clearCars(@RequestBody DateDays dd) {
		return company.clear(dd.date, dd.days);
	}

	// ---------------getCarDrivers---------------------------
	@GetMapping(value = CarsApiConstants.GET_CAR_DRIVERS + "/{carNumber}")
	List<DriverDto> getCarDrivers(@PathVariable(value = "carNumber") String carNumber) {
		return company.getCarDrivers(carNumber);
	}

	// ----------------getDriverCars--------------------------
	@GetMapping(value = CarsApiConstants.GET_DRIVER_CARS + "/{licenseId}")
	List<CarDto> getDriverCars(@PathVariable(value = "licenseId") long licenseId) {
		return company.getDriverCars(licenseId);
	}

	// ----------------getAllModels---------------------------
	@GetMapping(value = CarsApiConstants.GET_ALL_MODELS)
	List<String> getAllModels() {
		return company.getAllModelNames();
	}

	// ----------------getAllCars-----------------------------
	@GetMapping(value = CarsApiConstants.GET_ALL_CARS)
	Iterable<CarDto> getAllCars() {
		return company.getAllCars();
	}

	// ----------------getAllDrivers--------------------------
	@GetMapping(value = CarsApiConstants.GET_ALL_DRIVERS)
	List<DriverDto> getAllDrivers() {
		return company.getAllDrivers();
	}

	// ----------------getAllRecords--------------------------
	@GetMapping(value = CarsApiConstants.GET_ALL_RECORDS)
	List<RecordDto> getAllRecords() {
		return company.getAllRecords();
	}

	// ----------------getMostPopularModels-------------------
	@GetMapping(value = CarsApiConstants.MOST_POPULAR_MODELS)
	Iterable<String> getMostPopularModels() {
		return company.getMostPopularModelNames();
	}

	// -----------------getMostProfitModels------------------
	@GetMapping(value = CarsApiConstants.MOST_PROFIT_MODELS)
	Iterable<String> getMostProfitModels() {
		return company.getMostProfitModelNames();
	}

	// -----------------getModelProfit---------------------------
	@GetMapping(value = CarsApiConstants.GET_PROFIT_MODEL + "/{modelName}")
	double getModelProfit(@PathVariable(value = "modelName") String modelName) {
		return company.getModelProfit(modelName);
	}
	// -----------------------------------------------------------
}
