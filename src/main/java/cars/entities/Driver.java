package cars.entities;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Table(name = "drivers")
@Entity
public class Driver {
	@Id
	@ApiModelProperty(notes = "The driver's license as ID")
	private Long licenseId;
	@ApiModelProperty(notes = "The driver's name", required = true)
	private String name;
	@ApiModelProperty(notes = "The driver's birth year", required = true)
	private Integer birthYear;
	@ApiModelProperty(notes = "The driver's phone", required = true)
	private String phone;
	@OneToMany(mappedBy = "driver")
	@ApiModelProperty(notes = "The driver's list", required = true)
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
