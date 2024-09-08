package cars._security.service.auth;

import cars._security.controller.messages.AuthMessages;
import cars._security.dto.LoginDto;
import cars._security.dto.UserDto;
import cars._security.entity.User;
import cars._security.repository.UserRepository;
import cars._security.service.user.IUserManagement;
import cars._security.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final IUserManagement iUserManagement;

    public ResponseEntity<?> registration(UserDto userDto) {
        return iUserManagement.addUser(userDto);
    }

    public ResponseEntity<?> login(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername());
        if(user == null){
            return ResponseEntity.badRequest().body(AuthMessages.USER_IS_NOT_EXISTS);
        }
        if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            return ResponseEntity.badRequest().body(AuthMessages.PASSWORD_IS_INCORRECT);
        }
        String token = jwtTokenUtils.generateToken(user);
        return ResponseEntity.ok().body(token);
    }
}
