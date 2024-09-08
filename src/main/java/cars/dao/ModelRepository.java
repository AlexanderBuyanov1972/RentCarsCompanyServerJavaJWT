package cars.dao;


import cars.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
	Stream<Model> findAllBy();
	
	@Query("select e.modelName from Model e")
	Stream<String> getAllNames();

	

}
