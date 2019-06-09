package cars.entities.main;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Table(name = "models")
@Entity
public class Model {
    @Id
    @ApiModelProperty(notes = "The model's name as ID")
    private String modelName;
    @ApiModelProperty(notes = "The gas tank of the model", required = true)
    private int gasTank;
    @ApiModelProperty(notes = "The company of the model", required = true)
    private String company;
    @ApiModelProperty(notes = "The country of the model", required = true)
    private String country;
    @ApiModelProperty(notes = "The day's price of the model", required = true)
    private int priceDay;
    @ApiModelProperty(notes = "The model's list")
    @OneToMany(mappedBy = "model")
    private List<Car> cars;

    public Model() {
    }
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
