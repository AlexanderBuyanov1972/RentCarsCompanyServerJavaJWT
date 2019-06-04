package cars.entities.main;

import cars.dto.main.State;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "cars")
@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
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
	List<Record> records;

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public boolean isFlRemoved() {
		return flRemoved;
	}

	public void setFlRemoved(boolean flRemoved) {
		this.flRemoved = flRemoved;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public Car(String regNumber, String color, State state, boolean inUse, boolean flRemoved, Model model) {
		super();
		this.regNumber = regNumber;
		this.color = color;
		this.state = state;
		this.inUse = inUse;
		this.flRemoved = flRemoved;
		this.model = model;


	}

}
