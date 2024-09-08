package cars.controller.constants;

public interface CarsApi {
    // -------------Authorization and authentication---------------------------

    String SHUTDOWN = "/actuator/shutdown";//ADMIN
    //---------------------------------
    String CAR = "/car"; // MANAGER
    String MODEL = "/model"; // MANAGER
    String DRIVER = "/driver"; //CLERK
    //---------------------------------
    String CAR_RENT = "/car/rent";// CLERK
    String CAR_RETURN = "/car/return";// CLERK
    //-----------------------------------
    String REMOVE_CAR = "/car/remove";// MANAGER
    String CLEAR_CARS = "/cars/clear";// MANAGER
    //--------------------------------------
    String GET_DRIVER_CARS = "/driver/cars";// DRIVER
    String GET_CAR_DRIVERS = "/car/drivers";// DRIVER
    //--------------------------------------
    String GET_ALL_MODEL_NAMES = "/model_names";// ALL with not authentication
    String GET_ALL_MODELS = "/models";// ALL with not authentication
    String GET_ALL_CARS = "/cars";// authenticated
    String GET_ALL_DRIVERS = "/drivers";// DRIVER,CLERK
    String GET_ALL_RECORDS = "/records";// CLERK
    //-----------------------------------------
    String MOST_POPULAR_MODELS = "/models/popular";// STATIST
    String MOST_PROFIT_MODELS = "/models/profit";// STATIST
    String GET_PROFIT_MODEL = "/model/profit";// MANAGER,STATIST
}