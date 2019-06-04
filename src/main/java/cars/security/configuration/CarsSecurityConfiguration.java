package cars.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static cars.dto.main.CarsApiConstants.*;

@Configuration
public class CarsSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.httpBasic();
		httpSecurity.csrf().disable();
		// httpSecurity.authorizeRequests().anyRequest().authenticated();
		//httpSecurity.authorizeRequests().anyRequest().permitAll();
		httpSecurity.authorizeRequests().antMatchers(SHUTDOWN).hasRole("ADMIN");//+++
		httpSecurity.authorizeRequests().antMatchers(ADD_MODEL, ADD_CAR, REMOVE_CAR).hasRole("MANAGER");
		httpSecurity.authorizeRequests().antMatchers(ADD_DRIVER, RENT_CAR, RETURN_CAR).hasRole("CLERK");
		httpSecurity.authorizeRequests().antMatchers(GET_DRIVER_CARS, GET_CAR_DRIVERS).hasRole("DRIVER");//
		httpSecurity.authorizeRequests().antMatchers(MOST_POPULAR_MODELS, MOST_PROFIT_MODELS).hasRole("STATIST");//+++
		httpSecurity.authorizeRequests().antMatchers(GET_ALL_RECORDS).hasRole("TECHNICIAN");//+++
		// --------------------------------------------
		httpSecurity.authorizeRequests().antMatchers(GET_CAR, GET_ALL_CARS).authenticated();
		httpSecurity.authorizeRequests().antMatchers(GET_ALL_MODELS, GET_MODEL).permitAll();
		httpSecurity.authorizeRequests().antMatchers(GET_ALL_DRIVERS).hasAnyRole("DRIVER","CLERK");
		httpSecurity.authorizeRequests().antMatchers(GET_DRIVER).hasAnyRole("MANAGER", "CLERK");
		httpSecurity.authorizeRequests().antMatchers(GET_PROFIT_MODEL).hasAnyRole("MANAGER", "STATIST");
		 //------------------------------------------------
		
	}
}
