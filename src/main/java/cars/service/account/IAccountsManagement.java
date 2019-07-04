package cars.service.account;

import cars.dto.Response;

public interface IAccountsManagement {

    Response addAccount(String username, String password, String[] roles);

    Response removeAccount(String username);

    Response updatePassword(String username, String password);

    Response addRoles(String username, String[] roles);

    Response removeRoles(String username, String[] roles);

}
