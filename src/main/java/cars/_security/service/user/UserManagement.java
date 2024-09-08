package cars._security.service.user;

import cars._security.repository.UserRepository;
import cars._security.dto.UserDto;
import cars._security.entity.User;
import cars._security.controller.messages.AuthMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserManagement implements IUserManagement {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> addUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AuthMessages.USER_ALREADY_EXISTS);
        return ResponseEntity.ok().body(userRepository.save(makeUser(userDto)));
    }

    @Override
    public ResponseEntity<?> updateUser(UserDto userDto) {
        if (!userRepository.existsById(userDto.getUsername()))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userRepository.save(makeUser(userDto)));
    }


    @Override
    public ResponseEntity<?> getUser(String username) {
        User user = userRepository.findById(username).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new UserDto(user));
    }

    @Override
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @Override
    public ResponseEntity<?> removeUser(String username) {
        User user = userRepository.findById(username).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();
        userRepository.deleteById(username);
        return ResponseEntity.ok().build();
    }

    private User makeUser(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(userDto.getRoles())
                .date(LocalDateTime.now())
                .build();
    }
}
