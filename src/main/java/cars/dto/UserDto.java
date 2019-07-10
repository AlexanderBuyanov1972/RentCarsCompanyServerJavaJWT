package cars.dto;

public class UserDto {
	private String username;
	private String password;
	private String role;
	
	public UserDto() {

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
