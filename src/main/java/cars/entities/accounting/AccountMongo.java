package cars.entities.accounting;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class AccountMongo {
	@Id
	String username;
	String password;
	LocalDate date;
	Set<String> roles;

	public AccountMongo(String username, String password, Set<String> setRoles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = setRoles;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setRoles(Set<String> setRoles) {
		this.roles = setRoles;
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
