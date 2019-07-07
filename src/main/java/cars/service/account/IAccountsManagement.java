package cars.service.account;

import cars.dto.AccountDto;
import cars.dto.Response;

public interface IAccountsManagement {

    Response login(AccountDto accountDto);

    Response addAccount(AccountDto accountDto);

    Response updateAccount(AccountDto accountDto);

    Response removeAccount(String username);

    Response getAccount(String username);


}
