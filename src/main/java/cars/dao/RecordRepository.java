package cars.dao;

import cars.entities.Car;
import cars.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface RecordRepository extends JpaRepository<Record, Integer> {

	Stream<Record> findAllBy();

//-------------------clear of Yuri------------------------------
	List<Record> findByCarFlRemovedTrueAndReturnDateBefore(LocalDate dateDelete);

//------------------------------------------------------
	List<Record> findAllByCarRegNumber(String carNumber);

	List<Record> findAllByDriverLicenseId(Long licenseId);

	// --------------MostPopularModelsName------------------------
	@Query(value = "select count(*) from records join cars on reg_number=car_reg_number "
			+ "group by model_model_name order by count(*) limit 1", nativeQuery = true)
	Long getCountMaxForMostPopular();

	@Query(value = "select car.model.modelName from Record group by car.model.modelName"
			+ " having count(*)=:countMax", nativeQuery = false)
	List<String> getListMostPopular(@Param(value = "countMax") long countMax);

//	@Query(value = "select car.model from Record group by car.model"
//			+ " having count(*) =:countMax", nativeQuery = false)
//	List<Model> getListMostPopular(@Param(value = "countMax") long countMax);

	// ---------------ProfitFromModelNames-----------------------
//	@Query(value="select sum(cost) from Record group by car.model.modelName"
//			+ " having car.model.modelName = :modelName", nativeQuery = false)
	@Query(value = "select sum(cost) from records join cars on reg_number = car_reg_number"
			+ " group by model_model_name having model_model_name = :modelName", nativeQuery = true)
	Double getProfitFromModelName(@Param(value = "modelName") String modelName);

	// -------------MostProfitModelsName--------------------------
	@Query(value = "select sum(cost) from records join cars on reg_number = car_reg_number"
			+ " group by model_model_name order by sum(cost) desc limit 1", nativeQuery = true)
	Double getMaxProfitFromModelNames();

	@Query(value = "select model_model_name from records join cars on reg_number = car_reg_number"
			+ " group by model_model_name having sum(cost)=:profitMax", nativeQuery = true)
	List<String> getListStringMostProfitModelNames(@Param(value = "profitMax") Double profitMax);

//@Query(value = "select model_model_name from records join cars on reg_number = car_reg_number"
//		+ " group by model having sum(cost)=:profitMax", nativeQuery = true)
//List<Model> getListStringMostProfitModelNames(@Param(value = "profitMax") Double profitMax);
	// --------------My clear----------------------------------------------
	@Query(value="select distinct a from Car a join Record b on a.regNumber=b.car.regNumber "
			+ "where a.flRemoved = true and b.returnDate<:dateDelete",nativeQuery=false)
	List<Car> removeCars(@Param(value = "dateDelete") LocalDate dateDelete);
	// ------------------------------------------------------------------------------------
	List<Record> findByCarFlRemovedTrueOrReturnDateBefore(LocalDate dateDelete);

}
