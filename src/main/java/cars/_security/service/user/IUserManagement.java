package cars._security.service.user;

import cars._security.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface IUserManagement {

    ResponseEntity<?> addUser(UserDto userDto);

    ResponseEntity<?> updateUser(UserDto userDto);

    ResponseEntity<?> removeUser(String username);

    ResponseEntity<?> getUser(String username);

    ResponseEntity<?> getAllUser();


}
