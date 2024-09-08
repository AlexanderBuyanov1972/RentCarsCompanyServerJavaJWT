package cars.dao;

import cars.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

	Stream<Driver> findAllBy();

}
