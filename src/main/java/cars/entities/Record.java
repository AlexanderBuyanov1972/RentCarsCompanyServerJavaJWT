package cars.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "records")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Record(Driver driver, Car car, LocalDate rentDate, int rentDays) {
        this.driver = driver;
        this.car = car;
        this.rentDate = rentDate;
        this.rentDays = rentDays;
    }
}
