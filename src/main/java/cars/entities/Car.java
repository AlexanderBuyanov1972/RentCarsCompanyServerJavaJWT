package cars.entities;

import cars.dto.CarDto;
import cars.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "cars")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
    @JsonIgnore
    private List<Record> records;

    public Car(CarDto one, Model model) {
        this.regNumber = one.getRegNumber();
        this.state = one.getState();
        this.color = one.getColor();
        this.model = model;
        this.inUse = one.isInUse();
        this.flRemoved = one.isFlRemoved();
    }

}
