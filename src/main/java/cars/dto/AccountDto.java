package cars.dto;

import java.util.Arrays;

public class AccountDto {
	private String email;
	private String password;
	private String[] roles;
	
	public AccountDto() {
		super();
	}
	
	public AccountDto(String username, String password, String[] roles) {
		this.email = username;
		this.password = password;
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String[] getRoles() {
		return roles;
	}

	public AccountDto setEmail(String email) {
		this.email = email;
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
		return "AccountDto [email=" + email + ", password=" + password + ", roles=" + Arrays.toString(roles)
				+ "]";
	}

	

}
