package cars.service_rent_company;

import cars.dto.CarDto;
import cars.dto.DriverDto;
import cars.dto.ModelDto;
import cars.dto.RecordDto;
import org.springframework.http.ResponseEntity;

public interface IRentCompany {
    // *********************************************************
    ResponseEntity<?> addModel(ModelDto modelDto);// (OK,MODEL_EXISTS)1

    ResponseEntity<?> addCar(CarDto carDto);// (OK,CAR_EXISTS,NO_MODEL)2

    ResponseEntity<?> addDriver(DriverDto driverDto);// (OK,DRIVER_EXISTS)3
    // *********************************************************

    ResponseEntity<?> getModel(String modelName);//4

    ResponseEntity<?> getCar(String carNumber);//5

    ResponseEntity<?> getDriver(long licenseId);//6
    // *********************************************************

    ResponseEntity<?> rentCar(RecordDto recordDto);// (OK,CAR_IN_USE,NO_CAR,NO_DRIVER)7

    ResponseEntity<?> returnCar(RecordDto recordDto);// (OK,CAR_NOT_RENTED,8
    // RETURN_DATE_WRONG) In the case of damages up to 10% state is GOOD,
    // up to 30% state BAD more than 30% - remove car (flRemoved)

    ResponseEntity<?> removeCar(String carNumber);// (OK,CAR_IN_USE,CAR_NOT_EXISTS)9
    // removing car is setting flRemoved in true

    ResponseEntity<?> clear(int days);

    // all cars for which the returnDate before currentDate - days with10
    // flRemoved=true
    // are deleted from an information model along with all related records
    // it returns list of the deleted cars
    // *****************************************************************
    ResponseEntity<?> getCarDrivers(String carNumber); // returns11
    // all drivers that have been renting the car

    ResponseEntity<?> getDriverCars(long licenseId); // returns list of12
    // all cars that have been rented by the driver
    // *****************************************************************

    ResponseEntity<?> getAllRecords();

    ResponseEntity<?> getAllCars();

    ResponseEntity<?> getAllDrivers();

    ResponseEntity<?> getAllModels();

    ResponseEntity<?> getAllModelNames();
    // *****************************************************************

    ResponseEntity<?> getMostPopularModels();

    ResponseEntity<?> getModelProfit(String modelName);

    ResponseEntity<?> getMostProfitModels(); // returns list of most
    // proftable model names

}
