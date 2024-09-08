package cars._security.controller;

import cars._security.controller.constants.AuthApi;
import cars._security.dto.LoginDto;
import cars._security.dto.UserDto;
import cars._security.service.auth.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AuthApi.AUTH)
@Slf4j
public class AuthController {

    private final IAuthService iAuthService;

    @PostMapping(AuthApi.REGISTRATION)
    public ResponseEntity<?> registration(@RequestBody UserDto userDto) {
        return iAuthService.registration(userDto);
    }

    @PostMapping(AuthApi.LOGIN)
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return iAuthService.login(loginDto);
    }


}
