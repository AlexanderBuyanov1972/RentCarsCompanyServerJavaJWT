package cars.security.config;

import cars.security.accounting.IAccounting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


@Configuration
public class UserAuthentication implements UserDetailsService {
    @Autowired
    IAccounting iAccounting;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username------------------>" + username);
        String password = iAccounting.getPassword(username);
        System.out.println("password------------------>" + password);
        List<GrantedAuthority> roles = AuthorityUtils.createAuthorityList(iAccounting.getRole(username));
        System.out.println("roles------------------------>" + roles);
        return new User(username, password, roles);

    }

}

