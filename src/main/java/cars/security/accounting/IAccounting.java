package cars.security.accounting;

public interface IAccounting {
    String getPassword(String username);
    String getRole(String username);
}
