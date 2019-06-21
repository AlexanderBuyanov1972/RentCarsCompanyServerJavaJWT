package cars.service.accounting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncoderConfig {
	@Bean
	PasswordEncoder getPasswordEncoder() {
		System.out.println("возвращаю кодированный пароль");
		return new BCryptPasswordEncoder();
	}

}
