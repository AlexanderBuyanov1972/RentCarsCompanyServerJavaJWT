package cars.dto;

import java.util.Arrays;

public class AccountDto {
	private String username;
	private String password;
	private String[] roles;
	
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

	public AccountDto setUsername(String username) {
		this.username = username;
		return this;
	}

	public AccountDto setPassword(String password) {
		this.password = password;
		return this;
	}

	public AccountDto setRoles(String[] roles) {
		this.roles = roles;
		return this;
	}

	@Override
	public String toString() {
		return "AccountDto [username=" + username + ", password=" + password + ", roles=" + Arrays.toString(roles)
				+ "]";
	}

	

}
