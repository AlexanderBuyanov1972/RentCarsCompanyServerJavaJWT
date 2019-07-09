package cars.service.main;

import cars.dto.*;

public interface IRentCompany {
    // *********************************************************
    Response addModel(ModelDto modelDto);// (OK,MODEL_EXISTS)1

    Response addCar(CarDto carDto);// (OK,CAR_EXISTS,NO_MODEL)2

    Response addDriver(DriverDto driverDto);// (OK,DRIVER_EXISTS)3
    // *********************************************************

    Response getModel(String modelName);//4

    Response getCar(String carNumber);//5

    Response getDriver(long licenseId);//6
    // *********************************************************

    Response rentCar(RecordDto recordDto);// (OK,CAR_IN_USE,NO_CAR,NO_DRIVER)7

    Response returnCar(RecordDto recordDto);// (OK,CAR_NOT_RENTED,8
    // RETURN_DATE_WRONG) In the case of damages up to 10% state is GOOD,
    // up to 30% state BAD more than 30% - remove car (flRemoved)

    Response removeCar(String carNumber);// (OK,CAR_IN_USE,CAR_NOT_EXISTS)9
    // removing car is setting flRemoved in true

    Response clear(String date, int days);

    // all cars for which the returnDate before currentDate - days with10
    // flRemoved=true
    // are deleted from an information modelAccount along with all related records
    // it returns list of the deleted cars
    // *****************************************************************
    Response getCarDrivers(String carNumber); // returns11
    // all drivers that have been renting the car

    Response getDriverCars(long licenseId); // returns list of12
    // all cars that have been rented by the driver
    // *****************************************************************

    Response getAllRecords();

    Response getAllCars();

    Response getAllDrivers();

    Response getAllModels();

    Response getAllModelNames();
    // *****************************************************************

    Response getMostPopularModels();

    Response getModelProfit(String modelName);

    Response getMostProfitModels(); // returns list of most
    // proftable modelAccount names

}
