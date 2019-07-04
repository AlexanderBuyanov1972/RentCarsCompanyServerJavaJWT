package cars.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static cars.dto.constants.CarsApiConstants.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.cors();
        httpSecurity.csrf();

//        httpSecurity.authorizeRequests().anyRequest().permitAll();

        httpSecurity.authorizeRequests().antMatchers(GET_ALL_MODELS, GET_MODEL).authenticated().anyRequest().permitAll();
        httpSecurity.authorizeRequests().antMatchers(GET_CAR, GET_ALL_CARS).authenticated().anyRequest().authenticated();
        httpSecurity.authorizeRequests().antMatchers(SHUTDOWN, "/account").authenticated().anyRequest().hasRole("ADMIN");
        httpSecurity.authorizeRequests().antMatchers(ADD_MODEL, ADD_CAR, REMOVE_CAR,GET_PROFIT_MODEL).authenticated().anyRequest().hasRole("MANAGER");
        httpSecurity.authorizeRequests().antMatchers(ADD_DRIVER, RENT_CAR, RETURN_CAR,GET_ALL_DRIVERS, GET_DRIVER).authenticated().anyRequest().hasRole("CLERK");
        httpSecurity.authorizeRequests().antMatchers(GET_DRIVER_CARS, GET_CAR_DRIVERS).authenticated().anyRequest().hasRole("DRIVER");
        httpSecurity.authorizeRequests().antMatchers(MOST_POPULAR_MODELS, MOST_PROFIT_MODELS).authenticated().anyRequest().hasRole("STATIST");
        httpSecurity.authorizeRequests().antMatchers("/records").authenticated().anyRequest().hasRole("TECHNICIAN");
    }
}


