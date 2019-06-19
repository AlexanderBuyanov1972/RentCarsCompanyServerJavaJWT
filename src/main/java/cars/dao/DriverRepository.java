package cars.dao;

import cars.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface DriverRepository extends JpaRepository<Driver, Long> {

	Stream<Driver> findAllBy();

}
