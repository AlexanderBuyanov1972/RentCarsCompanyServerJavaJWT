package cars.entities.main;

import cars.dto.main.State;
import javax.persistence.*;
import java.util.List;

@Table(name = "cars")
@Entity
public class Car {

    @Id
    private String regNumber;
    private String color;
    @Enumerated(EnumType.STRING)
    private State state;
    private boolean inUse;
    private boolean flRemoved;
    @ManyToOne
    private Model model;
    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE) // physical removing car will cause removing
//all related records
    private List<Record> records;

    public Car() {
    }

    // *****************Getters****************************
    public String getRegNumber() {
        return regNumber;
    }

    public String getColor() {
        return color;
    }

    public State getState() {
        return state;
    }

    public boolean isInUse() {
        return inUse;
    }

    public boolean isFlRemoved() {
        return flRemoved;
    }

    public Model getModel() {
        return model;
    }

    public List<Record> getRecords() {
        return records;
    }

    // *****************Setters****************************
    public Car setRegNumber(String regNumber) {
        this.regNumber = regNumber;
        return this;
    }

    public Car setColor(String color) {
        this.color = color;
        return this;
    }

    public Car setState(State state) {
        this.state = state;
        return this;
    }

    public Car setInUse(boolean inUse) {
        this.inUse = inUse;
        return this;
    }

    public Car setFlRemoved(boolean flRemoved) {
        this.flRemoved = flRemoved;
        return this;
    }

    public Car setModel(Model model) {
        this.model = model;
        return this;
    }

    public Car setRecords(List<Record> records) {
        this.records = records;
        return this;
    }

}
