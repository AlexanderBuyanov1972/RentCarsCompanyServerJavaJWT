package cars.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Document(collection = "users")
public class AccountMongo {
	@Id
	private String username;
	private String password;
	private LocalDate date;
	private Set<String> roles;

	public AccountMongo(String username, String password, Set<String> setRoles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = setRoles;
	}

	public AccountMongo setPassword(String password) {
		this.password = password;
		return this;
	}

	public AccountMongo setDate(LocalDate date) {
		this.date = date;
		return this;
	}

	public AccountMongo setRoles(Set<String> setRoles) {
		this.roles = setRoles;
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

	public Set<String> getRoles() {
		return roles;
	}

	public AccountMongo() {
		super();
	}

	@Override
	public String toString() {
		return "AccountMongo [username=" + username + ", password=" + password + ", date=" + date + ", roles=" + roles
				+ "]";
	}
	
	

}
