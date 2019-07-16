package cars.service.account;

import cars.dto.Response;
import cars.dto.UserDto;

public interface IUserManagement {

    Response getUserRole(String username);

    Response addUser(UserDto userDto);

    Response updateUser(UserDto userDto);

    Response removeUser(String username);

    Response getUser(String username);


}
