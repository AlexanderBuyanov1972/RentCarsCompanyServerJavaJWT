package cars.dao;

import cars.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    Stream<Car> findAllBy();

    Car findByRegNumber(String regNumber);
}
