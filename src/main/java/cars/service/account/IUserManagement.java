package cars.service.account;

import cars.dto.Response;
import cars.dto.UserDto;
import cars.entities.User;

import java.util.Optional;

public interface IUserManagement {

    Optional<User> findByUsername(String username);

    Response addUser(UserDto userDto);

    Response updateUser(UserDto userDto);

    Response removeUser(String username);

    Response getUser(String username);

    void login(UserDto userDto);
}
