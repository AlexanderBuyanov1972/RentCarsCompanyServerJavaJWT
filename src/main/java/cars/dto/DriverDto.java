package cars.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class DriverDto {
    private long licenseId;
    private String name;
    private int birthYear;
    private String phone;

    public DriverDto() {
    }

    // **********Getters***************************
    public long getLicenseId() {
        return licenseId;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getPhone() {
        return phone;
    }

    // **********Setters***************************
    public DriverDto setLicenseId(long licenseId) {
        this.licenseId = licenseId;
        return this;
    }

    public DriverDto setName(String name) {
        this.name = name;
        return this;
    }

    public DriverDto setBirthYear(int birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public DriverDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

}
