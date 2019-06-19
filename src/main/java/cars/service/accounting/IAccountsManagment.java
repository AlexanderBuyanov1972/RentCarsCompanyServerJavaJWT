package cars.service.accounting;

import cars.dto.Response;

public interface IAccountsManagment {

    Response addAccount(String username, String password, String[] roles);

    Response removeAccount(String username);

    Response updatePassword(String username, String password);

    Response addRole(String username, String[] roles);

    Response removeRole(String username, String[] roles);

}
