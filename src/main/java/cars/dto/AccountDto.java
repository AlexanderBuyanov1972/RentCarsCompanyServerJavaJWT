package cars.dto;

import java.util.Arrays;

public class AccountDto {
	private String username;
	private String password;
	private String role;
	
	public AccountDto() {

	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public AccountDto setUsername(String username) {
		this.username = username;
		return this;
	}

	public AccountDto setPassword(String password) {
		this.password = password;
		return this;
	}

	public AccountDto setRole(String role) {
		this.role = role;
		return this;
	}



}
