package cars.security;


import cars.dao.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
public class UserAuthentication implements UserDetailsService {
    @Autowired
    IAccounting iAccounting;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = iAccounting.getPassword(username);
        System.out.println("password-------------->" + password);
        String[] roles = iAccounting.getRoles(username);
        System.out.println("roles------------------->" + roles[0]);
        return new User(username, password, AuthorityUtils.createAuthorityList(roles));
    }



}

