package cars.entities.main;

import cars.dto.main.State;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Table(name = "cars")
@Entity
public class Car {

    @Id
    @ApiModelProperty(notes = "The registration number as ID", required = true)
    private String regNumber;
    @ApiModelProperty(notes = "The car's color", required = true)
    private String color;
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "The car's state", required = true)
    private State state;
    @ApiModelProperty(notes = "The car's use", required = true)
    private boolean inUse;
    @ApiModelProperty(notes = "The car's remove", required = true)
    private boolean flRemoved;
    @ApiModelProperty(notes = "The model")
    @ManyToOne
    private Model model;
    @ApiModelProperty(notes = "The records's list")
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
