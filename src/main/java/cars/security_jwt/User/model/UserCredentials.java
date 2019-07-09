package cars.security_jwt.User.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class UserCredentials {

    @NotBlank(message = "Provide a usernname")
    @Column(unique=true)
    private String username;

    @NotBlank(message = "Provide a password")
    private String password;

    private String role;

    UserCredentials() {
    }

    public UserCredentials(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}