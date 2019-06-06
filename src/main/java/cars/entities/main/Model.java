package cars.entities.main;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "models")
@Entity
public class Model {

	@Id
	private String modelName;
	private int gasTank;
	private String company;
	private String country;
	private int priceDay;
	@OneToMany(mappedBy = "model")
	private List<Car> cars;

	public Model() { }

	// ********** Getters*************************

	public String getModelName() {
		return modelName;
	}
	public int getGasTank() {
		return gasTank;
	}
	public String getCompany() {
		return company;
	}
	public String getCountry() {
		return country;
	}
	public int getPriceDay() {
		return priceDay;
	}
	public List<Car> getCars() {
		return cars;
	}

	// ***********Setters*************************

	public Model setModelName(String modelName) {
		this.modelName = modelName;
		return this;
	}
	public Model setGasTank(int gasTank) {
		this.gasTank = gasTank;
		return this;
	}

	public Model setCompany(String company) {
		this.company = company;
		return this;
	}

	public Model setCountry(String country) {
		this.country = country;
		return this;
	}

	public Model setPriceDay(int priceDay) {
		this.priceDay = priceDay;
		return this;
	}
	public Model setCars(List<Car> cars) {
		this.cars = cars;
		return this;
	}
}
