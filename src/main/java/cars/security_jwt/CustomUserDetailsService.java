package cars.security_jwt;


import cars.entities.Account;
import cars.service.account.IAccountsManagement;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private IAccountsManagement iAccountsManagement;

    public CustomUserDetailsService(IAccountsManagement accountService) {
        this.iAccountsManagement = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return iAccountsManagement.findByName(username)
                .map(this::getUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found", username)));
    }

    private org.springframework.security.core.userdetails.User getUserDetails(Account account) {
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                getGrantedAuthorities(account));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Account account) {
        return AuthorityUtils
                .commaSeparatedStringToAuthorityList(account.getRole());
    }
}
