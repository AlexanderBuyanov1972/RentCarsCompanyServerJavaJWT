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
public class DriverDto {
	private long licenseId;
	private String name;
	private int birthYear;
	private String phone;

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

	public DriverDto(long licenseId, String name, int birthYear, String phone) {
		super();
		this.licenseId = licenseId;
		this.name = name;
		this.birthYear = birthYear;
		this.phone = phone;



	}

}
