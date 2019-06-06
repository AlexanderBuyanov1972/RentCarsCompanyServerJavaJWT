package cars.dto.main;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.time.LocalDate;
@ToString
@EqualsAndHashCode
public class RecordDto {
    private long licenseId;
    private String carNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
    private int gasTankPercent;
    private int rentDays;
    private float cost;
    private int damages;

    public RecordDto() {
    }

    // *****************Getters****************************
    public long getLicenseId() {
        return licenseId;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public String getCarNumber() {
        return carNumber;
    }
    public LocalDate getRentDate() {
        return rentDate;
    }
    public int getGasTankPercent() {
        return gasTankPercent;
    }
    public int getRentDays() {
        return rentDays;
    }
    public float getCost() {
        return cost;
    }
    public int getDamages() {
        return damages;
    }

// *****************Setters****************************
    public RecordDto setLicenseId(long licenseId) {
        this.licenseId = licenseId;
        return this;
    }
    public RecordDto setCarNumber(String carNumber) {
        this.carNumber = carNumber;
        return this;
    }
    public RecordDto setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
        return this;
    }
    public RecordDto setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }
    public RecordDto setGasTankPercent(int gasTankPercent) {
        this.gasTankPercent = gasTankPercent;
        return this;
    }
    public RecordDto setRentDays(int rentDays) {
        this.rentDays = rentDays;
        return this;
    }
    public RecordDto setCost(float cost) {
        this.cost = cost;
        return this;
    }
    public RecordDto setDamages(int damages) {
        this.damages = damages;
        return this;
    }

    }
