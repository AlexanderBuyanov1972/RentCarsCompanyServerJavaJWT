package cars.service.main;

import cars.dto.*;

public interface IRentCompany {
    // *********************************************************
    Response addModel(ModelDto modelDto);// (OK,MODEL_EXISTS)

    Response addCar(CarDto carDto);// (OK,CAR_EXISTS,NO_MODEL)

    Response addDriver(DriverDto driverDto);// (OK,DRIVER_EXISTS)
    // *********************************************************

    Response getModel(String modelName);

    Response getCar(String carNumber);

    Response getDriver(long licenseId);
    // *********************************************************

    Response rentCar(RecordDto recordDto);// (OK,CAR_IN_USE,NO_CAR,NO_DRIVER)

    Response returnCar(RecordDto recordDto);// (OK,CAR_NOT_RENTED,
    // RETURN_DATE_WRONG) In the case of damages up to 10% state is GOOD,
    // up to 30% state BAD more than 30% - remove car (flRemoved)

    Response removeCar(String carNumber);// (OK,CAR_IN_USE,CAR_NOT_EXISTS)
    // removing car is setting flRemoved in true

    Response clear(String date, int days);

    // all cars for which the returnDate before currentDate - days with
    // flRemoved=true
    // are deleted from an information model along with all related records
    // it returns list of the deleted cars
    // *****************************************************************
    Response getCarDrivers(String carNumber); // returns
    // all drivers that have been renting the car

    Response getDriverCars(long licenseId); // returns list of
    // all cars that have been rented by the driver
    // *****************************************************************

    Response getAllRecords();

    Response getAllCars();

    Response getAllDrivers();

    Response getAllModels();

    Response getAllModelNames();
    // *****************************************************************

    Response getMostPopularModels(); // returns list of
    // the model names the cars of which have been rented most times

    Response getModelProfit(String modelName); // returns value of money received from
    // the renting cars of a given model name

    Response getMostProfitModels(); // returns list of most
    // proftable model names

}
