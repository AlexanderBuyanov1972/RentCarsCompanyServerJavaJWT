package cars.dao;

import cars.entities.main.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface CarRepository extends JpaRepository<Car, String> {

	Stream<Car> findAllBy();

}
