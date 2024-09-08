package cars._security.service.auth;

import cars._security.dto.LoginDto;
import cars._security.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

    ResponseEntity<?> registration(UserDto userDto);

    ResponseEntity<?> login(LoginDto loginDto);
}