package cars.dto.main;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
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
public class RecordDto {
	private long licenseId;
	private String carNumber;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate rentDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate returnDate;
	private int gasTankPercent;
	private int rentDays;
	private float cost;
	private int damages;

	public long getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(long licenseId) {
		this.licenseId = licenseId;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public LocalDate getRentDate() {
		return rentDate;
	}

	public void setRentDate(LocalDate rentDate) {
		this.rentDate = rentDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public int getGasTankPercent() {
		return gasTankPercent;
	}

	public void setGasTankPercent(int gasTankPercent) {
		this.gasTankPercent = gasTankPercent;
	}

	public int getRentDays() {
		return rentDays;
	}

	public void setRentDays(int rentDays) {
		this.rentDays = rentDays;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getDamages() {
		return damages;
	}

	public void setDamages(int damages) {
		this.damages = damages;
	}

	public RecordDto(long licenseId, String carNumber, LocalDate rentDate, int rentDays) {
		super();
		this.licenseId = licenseId;
		this.carNumber = carNumber;
		this.rentDate = rentDate;
		this.rentDays = rentDays;


	}

	public RecordDto(long licenseId, String carNumber, LocalDate rentDate, LocalDate returnDate, int gasTankPercent, int rentDays, float cost, int damages) {
		this.licenseId = licenseId;
		this.carNumber = carNumber;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.gasTankPercent = gasTankPercent;
		this.rentDays = rentDays;
		this.cost = cost;
		this.damages = damages;
	}
}
