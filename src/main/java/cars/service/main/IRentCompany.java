package cars.service.main;

import cars.dto.main.*;

import java.time.LocalDate;
import java.util.List;

public interface IRentCompany {
	CarsReturnCode addModel(ModelDto modelDto);// (OK,MODEL_EXISTS)

	CarsReturnCode addCar(CarDto carDto);// (OK,CAR_EXISTS,NO_MODEL)

	CarsReturnCode addDriver(DriverDto driverDto);// (OK,DRIVER_EXISTS)

	ModelDto getModel(String modelName);

	CarDto getCar(String carNumber);

	DriverDto getDriver(long licenseId);

	CarsReturnCode rentCar(RecordDto recordDto);// (OK,CAR_IN_USE,NO_CAR,NO_DRIVER)

	CarsReturnCode returnCar(RecordDto recordDto);// (OK,CAR_NOT_RENTED,
	// RETURN_DATE_WRONG) In the case of damages up to 10% state is GOOD,
	// up to 30% state BAD more than 30% - remove car (flRemoved)

	CarsReturnCode removeCar(String carNumber);// (OK,CAR_IN_USE,CAR_NOT_EXISTS)
	// removing car is setting flRemoved in true

	List<CarDto> clear(LocalDate currentDate, int days);

	// all cars for which the returnDate before currentDate - days with
	// flRemoved=true
	// are deleted from an information model along with all related records
	// it returns list of the deleted cars
	List<DriverDto> getCarDrivers(String carNumber); // returns
	// all drivers that have been renting the car

	List<CarDto> getDriverCars(Long licenseId); // returns list of
	// all cars that have been rented by the driver

	List<RecordDto> getAllRecords();

	List<CarDto> getAllCars();

	List<DriverDto> getAllDrivers();

	List<String> getAllModelNames();

	List<String> getMostPopularModelNames(); // returns list of
	// the model names the cars of which have been rented most times

	Double getModelProfit(String modelName); // returns value of money received from
	// the renting cars of a given model name

	List<String> getMostProfitModelNames(); // returns list of most
	// proftable model names

}
