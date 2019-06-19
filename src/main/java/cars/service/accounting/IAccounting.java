package cars.service.accounting;

public interface IAccounting {

	String getPassword(String email);

	String[] getRoles(String email);
}
