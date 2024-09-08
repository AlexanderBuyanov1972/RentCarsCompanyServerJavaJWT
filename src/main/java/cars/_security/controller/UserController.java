package cars._security.controller;

import cars._security.controller.constants.AuthApi;
import cars._security.dto.UserDto;
import cars._security.service.user.IUserManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AuthApi.USER)
public class UserController {

    @Autowired
    private IUserManagement userManagement;

    @PostMapping()
    ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        return userManagement.addUser(userDto);
    }

    @PutMapping()
    ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        return userManagement.updateUser(userDto);
    }

    @GetMapping(AuthApi.USERNAME)
    ResponseEntity<?> getUserByUsername(@PathVariable(value = "username") String username) {
        return userManagement.getUser(username);
    }

    @DeleteMapping(AuthApi.USERNAME)
    ResponseEntity<?> removeUser(@PathVariable(value = "username") String username) {
        return userManagement.removeUser(username);
    }

    @GetMapping(AuthApi.ALL)
    ResponseEntity<?> getAllUser() {
        return userManagement.getAllUser();
    }
}
