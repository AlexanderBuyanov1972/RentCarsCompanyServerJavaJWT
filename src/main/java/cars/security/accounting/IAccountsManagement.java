package cars.security.accounting;

import cars.dto.Response;

public interface IAccountsManagement {

    Response addAccount(String email, String password, String[] roles);

    Response removeAccount(String email);

    Response updatePassword(String email, String password);

    Response addRoles(String email, String[] roles);

    Response removeRoles(String email, String[] roles);

}
