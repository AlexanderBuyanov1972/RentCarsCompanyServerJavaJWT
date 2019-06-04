package cars.service.accounting;

public interface IAccountsManagment {

	boolean addAccount(String username, String password, String[] roles);

	boolean removeAccount(String username);

	boolean updatePassword(String username, String password);

	boolean addRole(String username, String[] roles);

	boolean removeRole(String username, String[] roles);

}
