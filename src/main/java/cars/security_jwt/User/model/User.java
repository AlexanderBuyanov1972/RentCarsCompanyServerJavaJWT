package cars.security_jwt.User.model;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document(collection = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private UserCredentials userCredentials;

    protected User() {
    }
    public User(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }
    public String getId() {
        return id;
    }
    public UserCredentials getUserCredentials() {
        return userCredentials;
    }
}