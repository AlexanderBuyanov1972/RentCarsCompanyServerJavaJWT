package cars.dto;

import lombok.Data;

@Data
public class UserDto {
	private String username;
	private String password;
	private String role;
	
	public UserDto setUsername(String username) {
		this.username = username;
		return this;
	}

	public UserDto setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserDto setRole(String role) {
		this.role = role;
		return this;
	}



}
