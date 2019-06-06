package cars.entities.main;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "records")
@Entity
public class Record {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Car car;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
    private int gasTankPercent;
    private int rentDays;
    private float cost;
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
