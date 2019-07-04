package cars.security;

import cars.dao.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static cars.dto.constants.CarsApiConstants.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests().anyRequest().authenticated().antMatchers(SHUTDOWN, ADD_ACCOUNT, REMOVE_ACCOUNT,
                UPDATE_PASSWORD, ADD_ROLES, REMOVE_ROLES).hasRole("ADMIN");

    }

//        httpSecurity.httpBasic();
//
////        httpSecurity.authorizeRequests().anyRequest().permitAll();
//
//
//        httpSecurity.cors();
//        httpSecurity.csrf();
//        httpSecurity.authorizeRequests().anyRequest().authenticated();
//        httpSecurity.authorizeRequests().antMatchers(SHUTDOWN, ADD_ACCOUNT, REMOVE_ACCOUNT,
//                UPDATE_PASSWORD, ADD_ROLES, REMOVE_ROLES).hasRole("ADMIN");//+++
//        httpSecurity.authorizeRequests().antMatchers(ADD_MODEL, ADD_CAR, REMOVE_CAR).hasRole("MANAGER");
//        httpSecurity.authorizeRequests().antMatchers(ADD_DRIVER, RENT_CAR, RETURN_CAR).hasRole("CLERK");
//        httpSecurity.authorizeRequests().antMatchers(GET_DRIVER_CARS, GET_CAR_DRIVERS).hasRole("DRIVER");//
//        httpSecurity.authorizeRequests().antMatchers(MOST_POPULAR_MODELS, MOST_PROFIT_MODELS).hasRole("STATIST");//+++
//        httpSecurity.authorizeRequests().antMatchers(GET_ALL_RECORDS).hasRole("TECHNICIAN");//+++
//        // --------------------------------------------
//        httpSecurity.authorizeRequests().antMatchers(GET_CAR, GET_ALL_CARS).authenticated();
//        httpSecurity.authorizeRequests().antMatchers(GET_ALL_MODELS, GET_MODEL).permitAll();
//        httpSecurity.authorizeRequests().antMatchers(GET_ALL_DRIVERS).hasAnyRole("DRIVER", "CLERK");
//        httpSecurity.authorizeRequests().antMatchers(GET_DRIVER).hasAnyRole("MANAGER", "CLERK");
//        httpSecurity.authorizeRequests().antMatchers(GET_PROFIT_MODEL).hasAnyRole("MANAGER", "STATIST");
}




