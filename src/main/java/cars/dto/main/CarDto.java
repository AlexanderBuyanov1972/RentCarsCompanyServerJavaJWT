package cars.dto.main;

public class CarDto {
    private String regNumber;
    private String color;
    private State state;
    private String modelName;
    private boolean inUse;
    private boolean flRemoved;

    public CarDto() {
    }

    // **************** Getters*********************
    public String getRegNumber() {
        return regNumber;
    }

    public String getColor() {
        return color;
    }

    public State getState() {
        return state;
    }

    public String getModelName() {
        return modelName;
    }

    public boolean isInUse() {
        return inUse;
    }

    public boolean isFlRemoved() {
        return flRemoved;
    }

    // **************** Setters*********************
    public CarDto setRegNumber(String regNumber) {
        this.regNumber = regNumber;
        return this;
    }

    public CarDto setColor(String color) {
        this.color = color;
        return this;
    }

    public CarDto setState(State state) {
        this.state = state;
        return this;
    }

    public CarDto setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public CarDto setInUse(boolean inUse) {
        this.inUse = inUse;
        return this;
    }

    public CarDto setFlRemoved(boolean flRemoved) {
        this.flRemoved = flRemoved;
        return this;
    }
}
