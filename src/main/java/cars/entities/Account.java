package cars.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "users")
public class Account {
	@Id
	private String username;
	private String password;
	private LocalDate date;
	private String role;

	public Account() { }

	public String getUsername() {
		return username;
	}

	public Account setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Account setPassword(String password) {
		this.password = password;
		return this;
	}

	public LocalDate getDate() {
		return date;

	}

	public Account setDate(LocalDate date) {
		this.date = date;
		return this;
	}

	public String getRole() {
		return role;
	}

	public Account setRole(String role) {
		this.role = role;
		return this;
	}
}
