package cars.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
public class User {

    @Id
    private String username;
    private String password;
    private String role;
    private LocalDateTime date;

    public User() {
    }

//--------------------------getters-------------------------------
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public LocalDateTime getDate() { return date; }
//-------------------------setters-------------------------------
    public User setUsername(String username) {
        this.username = username;
        return this;
    }


    public User setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}