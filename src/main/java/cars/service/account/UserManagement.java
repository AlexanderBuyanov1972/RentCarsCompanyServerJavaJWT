package cars.service.account;

import cars.dao.UserRepository;
import cars.dto.Response;
import cars.dto.UserDto;
import cars.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserManagement implements IUserManagement {

    private static final String OK = "OK";
    private static final String USER_ALREADY_EXISTS = "user already exists";
    private static final String USER_IS_NOT_EXISTS = "user is not exists";
    private int goodCode = 200;
    private String currentDate = LocalDateTime.now().toString();

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder encoder;


    @Override
    public Response addUser(UserDto userDto) {
        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        User user = userRepository.findById(userDto.getUsername()).orElse(null);
        if (user != null)
            return response.setMessage(USER_ALREADY_EXISTS);
        user = new User()
                .setUsername(userDto.getUsername())
                .setPassword(encoder.encode(userDto.getPassword()))
                .setDate(LocalDateTime.now());
        if (userDto.getRole() != null && !userDto.getRole().equals(""))
            user.setRole(userDto.getRole());
        userRepository.save(user);
        return response.setMessage(OK);
    }

    @Override
    public Response updateUser(UserDto userDto) {
        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        if (!userRepository.existsById(userDto.getUsername()))
            return response.setMessage(USER_IS_NOT_EXISTS);
        User user = new User()
                .setUsername(userDto.getUsername())
                .setPassword(encoder.encode(userDto.getPassword()))
                .setRole(userDto.getRole()).setDate(LocalDateTime.now());
        userRepository.save(user);
        return response.setMessage(OK);

    }

    @Override
    public Response getUser(String username) {
        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        User user = userRepository.findById(username).orElse(null);
        if (user == null)
            return response.setMessage(USER_IS_NOT_EXISTS);
        UserDto accountDto = new UserDto().setUsername(username)
                .setPassword(user.getPassword()).setRole(user.getRole());
        return response.setContent(accountDto).setMessage(OK);
    }

    @Override
    public Response removeUser(String username) {
        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        User user = userRepository.findById(username).orElse(null);
        if (user == null)
            return response.setMessage(USER_IS_NOT_EXISTS);
        userRepository.deleteById(username);
        return response.setMessage(OK);
    }

    @Override
    public Response getUserRole(String username) {
        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        User user = userRepository.findById(username).orElse(null);
        if (user == null)
            return response.setMessage(USER_IS_NOT_EXISTS);
        return response.setMessage(OK).setContent(user.getRole());
    }
}
