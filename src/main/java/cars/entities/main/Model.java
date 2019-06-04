package cars.entities.main;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "models")
@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Model {

	@Id
	private String modelName;
	private int gasTank;
	private String company;
	private String country;
	private int priceDay;
	@OneToMany(mappedBy = "model")
	List<Car> cars;

	public Model(String modelName, int gasTank, String company, String country, int priceDay) {
		super();
		this.modelName = modelName;
		this.gasTank = gasTank;
		this.company = company;
		this.country = country;
		this.priceDay = priceDay;


	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getGasTank() {
		return gasTank;
	}

	public void setGasTank(int gasTank) {
		this.gasTank = gasTank;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPriceDay() {
		return priceDay;
	}

	public void setPriceDay(int priceDay) {
		this.priceDay = priceDay;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
}
