package cars.security_jwt.config;


import cars.security_jwt.CustomUserDetailsService;
import cars.security_jwt.TokenProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletResponse;

import static cars.dto.constants.CarsApiConstants.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${cors.enabled:false}")
    private boolean corsEnabled;

    private final TokenProperties tokenProperties;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(TokenProperties tokenProperties,
                          BCryptPasswordEncoder passwordEncoder,
                          CustomUserDetailsService userDetailsService) {
        this.tokenProperties = tokenProperties;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors();
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedResponse());

                        //httpSecurity.authorizeRequests().anyRequest().permitAll();

        httpSecurity.authorizeRequests().antMatchers
                (GET_MODEL + "/**", GET_ALL_MODELS, ACCOUNT + LOGIN, ACCOUNT + REGISTRATION).permitAll();

        httpSecurity.authorizeRequests().antMatchers(GET_CAR + "/**", GET_ALL_CARS).authenticated();

        httpSecurity.authorizeRequests().antMatchers(SHUTDOWN, ACCOUNT).hasRole("ADMIN");

        httpSecurity.authorizeRequests().antMatchers (ADD_MODEL, ADD_CAR, REMOVE_CAR +"/**", CLEAR_CARS + "/**", GET_PROFIT_MODEL + "/**").hasRole("MANAGER");

        httpSecurity.authorizeRequests().antMatchers (ADD_DRIVER, RENT_CAR, RETURN_CAR, GET_ALL_DRIVERS, GET_DRIVER  +"/**").hasRole("CLERK");

        httpSecurity.authorizeRequests().antMatchers (GET_DRIVER_CARS + "/**", GET_CAR_DRIVERS + "/**",GET_ALL_MODEL_NAMES).hasRole("DRIVER");

        httpSecurity.authorizeRequests().antMatchers (MOST_POPULAR_MODELS, MOST_PROFIT_MODELS).hasRole("STATIST");

        httpSecurity.authorizeRequests().antMatchers (GET_ALL_RECORDS).hasRole("TECHNICIAN");
    }

//    private HttpSecurity applyCors(HttpSecurity httpSecurity) throws Exception {
//        if (corsEnabled) {
//            return httpSecurity.cors().and();
//        } else {
//            return httpSecurity;
//        }
//    }

    private AuthenticationEntryPoint unauthorizedResponse() {
        return (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
