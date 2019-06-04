package cars.service.entry;

public interface IAccounting {
	String getPassword(String username);

	String[] getRoles(String username);
}
