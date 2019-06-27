package cars.security.accounting;

public interface IAccounting {
    String getPassword(String email);

    String[] getRoles(String email);
}
