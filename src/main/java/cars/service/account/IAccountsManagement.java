package cars.service.account;

import cars.dto.AccountDto;
import cars.dto.Response;
import cars.entities.Account;

import java.util.Optional;

public interface IAccountsManagement {
    Account register(AccountDto accountDto);

    Optional<Account> findByName(String username);

    Response login(AccountDto accountDto);

    Response addAccount(AccountDto accountDto);

    Response updateAccount(AccountDto accountDto);

    Response removeAccount(String username);

    Response getAccount(String username);


}
