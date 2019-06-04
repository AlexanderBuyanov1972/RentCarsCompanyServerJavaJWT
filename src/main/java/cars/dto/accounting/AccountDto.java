package cars.dto.accounting;

import java.util.Arrays;

public class AccountDto {
	String username;
	String password;
	String[] roles;
	
	public AccountDto() {
		super();
	}
	
	public AccountDto(String username, String password, String[] roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}


	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "AccountDto [username=" + username + ", password=" + password + ", roles=" + Arrays.toString(roles)
				+ "]";
	}

	

}
