package cars.entities.main;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "drivers")
@Entity
public class Driver {
	@Id
	private Long licenseId;
	private String name;
	private Integer birthYear;
	private String phone;
	@OneToMany(mappedBy = "driver")
	private List<Record> records;

	public Driver() { }

	// *****************Getters****************************
	public Long getLicenseId() {
		return licenseId;
	}
	public String getName() {
		return name;
	}
	public Integer getBirthYear() {
		return birthYear;
	}
	public String getPhone() {
		return phone;
	}
	public List<Record> getRecords() {
		return records;
	}
	// *****************Setters****************************
	public Driver setLicenseId(Long licenseId) {
		this.licenseId = licenseId;
		return this;
	}
	public Driver setName(String name) {
		this.name = name;
		return this;
	}
	public Driver setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
		return this;
	}
	public Driver setPhone(String phone) {
		this.phone = phone;
		return this;
	}
	public Driver setRecords(List<Record> records) {
		this.records = records;
		return this;
	}
}
