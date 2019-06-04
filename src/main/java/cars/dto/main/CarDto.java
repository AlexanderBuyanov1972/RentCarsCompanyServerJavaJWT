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

public class CarDto {
	private String regNumber;
	private String color;
	private State state;
	private String modelName;
	private boolean inUse;
	private boolean flRemoved;

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

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public void setFlRemoved(boolean flRemoved) {
		this.flRemoved = flRemoved;
	}

	public CarDto(String regNumber, String color, String modelName) {
		this.regNumber = regNumber;
		this.color = color;
		this.modelName = modelName;
		state = State.EXCELLENT;


	}

}
