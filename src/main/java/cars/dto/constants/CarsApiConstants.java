package cars.dto.constants;

public interface CarsApiConstants {
	// ----------------------------------------
	String SHUTDOWN = "/actuator/shutdown";//ADMIN
	String ACCOUNT = "/account"; // ADMIN
	//---------------------------------
	String ADD_CAR = "/car"; // MANAGER
	String ADD_MODEL = "/model"; // MANAGER
	String ADD_DRIVER = "/driver"; //CLERK
	//---------------------------------
	String GET_MODEL = "/model";// ALL with not authentication
	String GET_CAR = "/car";//authenticated
	String GET_DRIVER = "/driver";// MANAGER,CLERK
	//---------------------------------
	String RENT_CAR = "/car/rent";// CLERK
	String RETURN_CAR = "/car/return";// CLERK
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
	String GET_ALL_RECORDS = "/records";// TECHNICIAN
	//-----------------------------------------
	String MOST_POPULAR_MODELS = "/models/popular";// STATIST
	String MOST_PROFIT_MODELS = "/models/profit";// STATIST
	String GET_PROFIT_MODEL = "/model/profit";// MANAGER,STATIST
	//------------------------------------------

}