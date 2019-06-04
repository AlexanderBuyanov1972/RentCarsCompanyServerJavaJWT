package cars.dto.main;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ModelDto {
	private String modelName;
	private int gasTank;
	private String company;
	private String country;
	private int priceDay;

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


	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setGasTank(int gasTank) {
		this.gasTank = gasTank;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPriceDay(int priceDay) {
		this.priceDay = priceDay;
	}

	public ModelDto(String modelName, int gasTank, String company, String country, int priceDay) {
		super();
		this.modelName = modelName;
		this.gasTank = gasTank;
		this.company = company;
		this.country = country;
		this.priceDay = priceDay;


	}

}
