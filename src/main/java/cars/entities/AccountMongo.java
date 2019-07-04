package cars.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "users")
public class AccountMongo {
	@Id
	private String username;
	private String password;
	private LocalDate date;
	private String[] roles;

	public AccountMongo(String email, String password, String[] roles) {
		super();
		this.username = email;
		this.password = password;
		this.roles = roles;
	}

	public AccountMongo setPassword(String password) {
		this.password = password;
		return this;
	}

	public AccountMongo setDate(LocalDate date) {
		this.date = date;
		return this;
	}

	public AccountMongo setRoles(String[] roles) {
		this.roles = roles;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public LocalDate getDate() {
		return date;
	}

	public String[] getRoles() {
		return roles;
	}

	public AccountMongo() {

	}


	

}
