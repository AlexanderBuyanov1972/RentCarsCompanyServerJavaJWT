package cars.entities;

import cars.dto.DriverDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "drivers")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Driver {
    @Id
    private Long licenseId;
    private String name;
    private Integer birthYear;
    private String phone;
    @OneToMany(mappedBy = "driver")
    @JsonIgnore
    private List<Record> records;

    public Driver(DriverDto one) {
        this.licenseId = one.getLicenseId();
        this.name = one.getName();
        this.birthYear = one.getBirthYear();
        this.phone = one.getPhone();
    }
}
