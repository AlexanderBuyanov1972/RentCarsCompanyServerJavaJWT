package cars.dto;

import cars.entities.Record;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RecordDto {
    private long licenseId;
    private String regNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
    private int gasTankPercent;
    private int rentDays;
    private float cost;
    private int damages;

    public RecordDto(Record record) {
        this.licenseId = record.getDriver().getLicenseId();
        this.regNumber = record.getCar().getRegNumber();
        this.rentDate = record.getRentDate();
        this.returnDate = record.getReturnDate();
        this.gasTankPercent = record.getGasTankPercent();
        this.rentDays = record.getRentDays();
        this.cost = record.getCost();
        this.damages = record.getDamages();

    }
}
