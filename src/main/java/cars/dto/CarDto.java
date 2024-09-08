package cars.dto;

import cars.entities.Car;
import cars.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CarDto {
    private String regNumber;
    private String color;
    private State state;
    private String modelName;
    private boolean inUse;
    private boolean flRemoved;

    public CarDto(Car car) {
        this.regNumber = car.getRegNumber();
        this.state = car.getState();
        this.color = car.getColor();
        this.modelName = car.getModel().getModelName();
        this.inUse = car.isInUse();
        this.flRemoved = car.isFlRemoved();
    }

}
