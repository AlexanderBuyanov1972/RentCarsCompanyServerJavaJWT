package cars.dto;

import cars.entities.Driver;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DriverDto {
    private long licenseId;
    private String name;
    private int birthYear;
    private String phone;

    public DriverDto(Driver driver) {
        this.licenseId = driver.getLicenseId();
        this.name = driver.getName();
        this.birthYear = driver.getBirthYear();
        this.phone = driver.getPhone();
    }
}
