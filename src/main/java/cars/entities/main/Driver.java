package cars.entities.main;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "drivers")
@Entity

@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Driver {
	@Id
	private Long licenseId;
	private String name;
	private Integer birthYear;
	private String phone;
	@OneToMany(mappedBy = "driver")
	List<Record> records;

	public Driver(Long licenseId, String name, Integer birthYear, String phone) {
		super();
		this.licenseId = licenseId;
		this.name = name;
		this.birthYear = birthYear;
		this.phone = phone;


	}

	public Long getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(Long licenseId) {
		this.licenseId = licenseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}
}
