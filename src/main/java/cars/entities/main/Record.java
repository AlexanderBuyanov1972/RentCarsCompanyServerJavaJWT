package cars.entities.main;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "records")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Record {

	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private Driver driver;
	@ManyToOne
	private Car car;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate rentDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate returnDate;
	private int gasTankPercent;
	private int rentDays;
	private float cost;
	private int damages;

	public Record(Driver driver, Car car, LocalDate rentDate, int rentDays) {
		super();
		this.driver = driver;
		this.car = car;
		this.rentDate = rentDate;
		this.rentDays = rentDays;


	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public LocalDate getRentDate() {
		return rentDate;
	}

	public void setRentDate(LocalDate rentDate) {
		this.rentDate = rentDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public int getGasTankPercent() {
		return gasTankPercent;
	}

	public void setGasTankPercent(int gasTankPercent) {
		this.gasTankPercent = gasTankPercent;
	}

	public int getRentDays() {
		return rentDays;
	}

	public void setRentDays(int rentDays) {
		this.rentDays = rentDays;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getDamages() {
		return damages;
	}

	public void setDamages(int damages) {
		this.damages = damages;
	}
}
