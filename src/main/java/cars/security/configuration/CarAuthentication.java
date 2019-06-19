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
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		String password = iAccounting.getPassword(email);
		return new User(email, password, AuthorityUtils.createAuthorityList(iAccounting.getRoles(email)));
	}
}
