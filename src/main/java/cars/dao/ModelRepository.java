package cars.dao;


import cars.entities.main.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;


public interface ModelRepository extends JpaRepository<Model, String> {
	Stream<Model> findAllBy();
	
	@Query("select e.modelName from Model e")
	Stream<String> getAllNames();

	

}
