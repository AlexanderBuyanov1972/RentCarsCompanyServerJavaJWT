package cars.security.security_config;


import cars.security.security_jwt.AuthenticationFilter;
import cars.security.security_jwt.AuthorizationFilter;
import cars.security.security_jwt.CustomUserDetailsService;
import cars.security.security_jwt.TokenProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

import static cars.dto.constants.CarsApiConstants.*;

//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, tokenProperties.getLoginPath()).permitAll();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.exceptionHandling().authenticationEntryPoint(unauthorizedResponse());
        httpSecurity.logout();
        httpSecurity.addFilter(new AuthenticationFilter(authenticationManagerBean(), tokenProperties));
        httpSecurity.addFilterAfter(new AuthorizationFilter(tokenProperties), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, tokenProperties.getLoginPath()).permitAll();
        httpSecurity.authorizeRequests().antMatchers(GET_ALL_MODELS, GET_MODEL, ACCOUNT + GET_ROLE).permitAll();
        httpSecurity.authorizeRequests().antMatchers(GET_CAR, GET_ALL_CARS).authenticated();
        httpSecurity.authorizeRequests().antMatchers(SHUTDOWN, ACCOUNT).hasRole("ADMIN");
        httpSecurity.authorizeRequests().antMatchers(ADD_MODEL, ADD_CAR, REMOVE_CAR, CLEAR_CARS, GET_PROFIT_MODEL).hasRole("MANAGER");
        httpSecurity.authorizeRequests().antMatchers(ADD_DRIVER, RENT_CAR, RETURN_CAR, GET_ALL_DRIVERS, GET_DRIVER, GET_ALL_RECORDS).hasRole("CLERK");
        httpSecurity.authorizeRequests().antMatchers(GET_DRIVER_CARS, GET_CAR_DRIVERS).hasRole("DRIVER");
        httpSecurity.authorizeRequests().antMatchers(MOST_POPULAR_MODELS, MOST_PROFIT_MODELS).hasRole("STATIST");



    }

    private AuthenticationEntryPoint unauthorizedResponse() {
        return (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}