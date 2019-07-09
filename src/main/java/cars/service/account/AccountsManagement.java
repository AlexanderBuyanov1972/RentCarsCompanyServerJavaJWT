package cars.service.account;

import cars.security_jwt.User.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class AccountsManagement implements IAccountsManagement {
    private static final String OK = "OK";
    private static final String USER_ALREADY_EXISTS = "user already exists";
    private static final String USER_IS_NOT_EXISTS = "user is not exists";
    private static final String ACCOUNT_IS_NOT_EXISTS = "account is not exists";
    private static final String PASSWORD_IS_WRONG = "password is wrong";
    private int goodCode = 200;
    private String currentDate = LocalDateTime.now().toString();

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder encoder;



//    @Override
//    public Response login(AccountDto accountDto) {
//        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
//        User user = userRepository.findById(accountDto.getUsername()).orElse(null);
//        if (user == null)
//            return response.setMessage(USER_IS_NOT_EXISTS);
//        if (encoder.matches(accountDto.getPassword(), user.getUserCredentials().getPassword()))
//            return response.setContent(user.getUserCredentials().getRole()).setMessage(OK);
//        return response.setMessage(PASSWORD_IS_WRONG);
//
//    }

//    @Override
//    public Response addAccount(AccountDto accountDto) {
//        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
//        User user = userRepository.findById(accountDto.getUsername()).orElse(null);
//        if (user != null)
//            return response.setMessage(USER_ALREADY_EXISTS);
//        user = new User()
//                .setUsername(accountDto.getUsername())
//                .setPassword(encoder.encode(accountDto.getPassword()))
//                .setRole(accountDto.getRole()).setDate(LocalDate.now());
//        userRepository.save(user);
//        return response.setMessage(OK);
//    }
//
//    @Override
//    public Response updateAccount(AccountDto accountDto) {
//        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
//        if (!userRepository.existsById(accountDto.getUsername()))
//            return response.setMessage(ACCOUNT_IS_NOT_EXISTS);
//        User account = new User()
//                .setUsername(accountDto.getUsername())
//                .setPassword(encoder.encode(accountDto.getPassword()))
//                .setRole(accountDto.getRole()).setDate(LocalDate.now());
//        userRepository.save(account);
//        return response.setMessage(OK);
//
//    }
//
//    @Override
//    public Response getAccount(String username) {
//        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
//        User user = userRepository.findById(username).orElse(null);
//        if (user == null)
//            return response.setMessage(ACCOUNT_IS_NOT_EXISTS);
//        AccountDto accountDto = new AccountDto().setUsername(username)
//                .setPassword(user.getPassword()).setRole(user.getRole());
//        return response.setContent(accountDto).setMessage(OK);
//    }
//
//    @Override
//    public Response removeAccount(String username) {
//        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
//        User user = userRepository.findById(username).orElse(null);
//        if (user == null)
//            return response.setMessage(USER_IS_NOT_EXISTS);
//        userRepository.deleteById(username);
//        return response.setMessage(OK);
//    }

}
