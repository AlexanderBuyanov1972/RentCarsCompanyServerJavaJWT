package cars.security.configuration;

import cars.service.accounting.IAccounting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
public class CarAuthentication implements UserDetailsService {
    @Autowired
    IAccounting iAccounting;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("вощёл в юзер дитаилс");
        String password = iAccounting.getPassword(username);
        System.out.println("получили пароль для юзера");
        return new User(username, password, AuthorityUtils.createAuthorityList(iAccounting.getRoles(username)));
    }

}

