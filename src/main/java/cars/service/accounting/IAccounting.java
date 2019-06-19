package cars.service.accounting;

public interface IAccounting {

	String getPassword(String username);

	String[] getRoles(String username);
}
