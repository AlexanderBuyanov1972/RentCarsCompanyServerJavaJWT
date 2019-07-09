package cars.service.account;

import cars.dao.AccountRepository;
import cars.dto.AccountDto;
import cars.dto.Response;
import cars.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


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
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder encoder;

    // -----------------------------------jwt----------------------------------------------------------
    @Override
    public Account register(AccountDto accountDto) {
        Account account = accountRepository.findById(accountDto.getUsername()).orElse(null);
        if (account != null) {
            return null;
        }
        account = new Account().setUsername(accountDto.getUsername())
                .setPassword(encoder.encode(accountDto.getPassword()))
                .setRole(accountDto.getRole())
                .setDate(LocalDate.now());
        accountRepository.save(account);
        return account;
    }

    @Override
    public Optional<Account> findByName(String username) {
        return accountRepository.findById(username);
    }

    // -----------------------------------------------------------------------------------------------------

    @Override
    public Response login(AccountDto accountDto) {
        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        Account account = accountRepository.findById(accountDto.getUsername()).orElse(null);
        if (account == null)
            return response.setMessage(USER_IS_NOT_EXISTS);
        if (encoder.matches(accountDto.getPassword(), account.getPassword()))
            return response.setContent(account.getRole()).setMessage(OK);
        return response.setMessage(PASSWORD_IS_WRONG);

    }

    @Override
    public Response addAccount(AccountDto accountDto) {
        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        Account account = accountRepository.findById(accountDto.getUsername()).orElse(null);
        if (account != null)
            return response.setMessage(USER_ALREADY_EXISTS);
        account = new Account()
                .setUsername(accountDto.getUsername())
                .setPassword(encoder.encode(accountDto.getPassword()))
                .setRole(accountDto.getRole()).setDate(LocalDate.now());
        accountRepository.save(account);
        return response.setMessage(OK);
    }

    @Override
    public Response updateAccount(AccountDto accountDto) {
        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        if (!accountRepository.existsById(accountDto.getUsername()))
            return response.setMessage(ACCOUNT_IS_NOT_EXISTS);
        Account account = new Account()
                .setUsername(accountDto.getUsername())
                .setPassword(encoder.encode(accountDto.getPassword()))
                .setRole(accountDto.getRole()).setDate(LocalDate.now());
        accountRepository.save(account);
        return response.setMessage(OK);

    }

    @Override
    public Response getAccount(String username) {
        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        Account account = accountRepository.findById(username).orElse(null);
        if (account == null)
            return response.setMessage(ACCOUNT_IS_NOT_EXISTS);
        AccountDto accountDto = new AccountDto().setUsername(username)
                .setPassword(account.getPassword()).setRole(account.getRole());
        return response.setContent(accountDto).setMessage(OK);
    }

    @Override
    public Response removeAccount(String username) {
        Response response = new Response().setCode(goodCode).setTimestamp(currentDate).setContent("");
        Account account = accountRepository.findById(username).orElse(null);
        if (account == null)
            return response.setMessage(USER_IS_NOT_EXISTS);
        accountRepository.deleteById(username);
        return response.setMessage(OK);
    }

}
