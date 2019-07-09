package cars.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import java.time.LocalDate;

@Document(collection = "users")
public class Account {
    @Id
    private String username;
    private String password;
    private String role;
    private LocalDate date;

    public Account() {
    }

    public Account(String username, String password, String role, LocalDate date) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.date = date;
    }

    // ---------------------getters-----------------------------------
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public LocalDate getDate() {
        return date;
    }

    // ----------------------setters----------------------------
    public Account setUsername(String username) {
        this.username = username;
        return this;
    }

    public Account setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    public Account setRole(String role) {
        this.role = role;
        return this;
    }

}
