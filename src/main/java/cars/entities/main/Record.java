package cars.entities.main;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "records")
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The record's id as ID")
    private int id;
    @ManyToOne
    @ApiModelProperty(notes = "The record's driver", required = true)
    private Driver driver;
    @ManyToOne
    @ApiModelProperty(notes = "The record's car")
    private Car car;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "The record's rent date")
    private LocalDate rentDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "The record's return date")
    private LocalDate returnDate;
    @ApiModelProperty(notes = "The record's gas tank percent")
    private int gasTankPercent;
    @ApiModelProperty(notes = "The record's rent days")
    private int rentDays;
    @ApiModelProperty(notes = "The record's cost")
    private float cost;
    @ApiModelProperty(notes = "The record's damages")
    private int damages;
    public Record() { }

    // ************Getters************************
    public int getId() {
        return id;
    }
    public Driver getDriver() {
        return driver;
    }
    public Car getCar() {
        return car;
    }
    public LocalDate getRentDate() {
        return rentDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public int getGasTankPercent() {
        return gasTankPercent;
    }
    public int getRentDays() {
        return rentDays;
    }
    public float getCost() {
        return cost;
    }
    public int getDamages() {
        return damages;
    }
    // ************Setters************************
    public Record setId(int id) {
        this.id = id;
        return this;
    }
    public Record setDriver(Driver driver) {
        this.driver = driver;
        return this;
    }
    public Record setCar(Car car) {
        this.car = car;
        return this;
    }
    public Record setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
        return this;
    }
    public Record setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }
    public Record setGasTankPercent(int gasTankPercent) {
        this.gasTankPercent = gasTankPercent;
        return this;
    }
    public Record setRentDays(int rentDays) {
        this.rentDays = rentDays;
        return this;
    }
    public Record setCost(float cost) {
        this.cost = cost;
        return this;
    }
    public Record setDamages(int damages) {
        this.damages = damages;
        return this;
    }
}
