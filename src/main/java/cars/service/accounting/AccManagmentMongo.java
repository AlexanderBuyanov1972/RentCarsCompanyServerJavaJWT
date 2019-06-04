package cars.service.accounting;

import cars.dao.AuthRepository;
import cars.entities.accounting.AccountMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;



@Service
public class AccManagmentMongo implements IAccountsManagment {
	@Autowired
	AuthRepository repository;
	@Autowired
	PasswordEncoder encoder;

	@Override
	public boolean addAccount(String username, String password, String[] roles) {
		if (repository.existsById(username))
			return false;
		String encoderPassword = encoder.encode(password);
		Set<String> setRoles = new HashSet<>(Arrays.asList(roles));
		AccountMongo account = new AccountMongo(username, encoderPassword, setRoles);
		account.setDate(LocalDate.now());
		repository.save(account);
		return true;
	}

	@Override
	public boolean removeAccount(String username) {
		if (!repository.existsById(username))
			return false;
		repository.deleteById(username);
		return true;
	}

	@Override
	public boolean updatePassword(String username, String password) {
		AccountMongo account = repository.findById(username).orElse(null);
		if (account == null)
			return false;
		if (encoder.matches(password, account.getPassword()))
			return false;
		account.setPassword(encoder.encode(password));
		account.setDate(LocalDate.now());
		repository.save(account);
		return true;
	}

	@Override
	public boolean addRole(String username, String[] role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeRole(String username, String[] role) {
		// TODO Auto-generated method stub
		return false;
	}

}
