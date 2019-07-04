package cars.security;

public interface IAccounting {
    String getPassword(String username);

    String[] getRoles(String username);
}
