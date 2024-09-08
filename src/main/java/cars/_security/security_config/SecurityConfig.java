package cars._security.security_config;

import cars._security.controller.constants.AuthApi;
import cars._security.filters.AuthenticationFilter;
import cars._security.user_details_service.UserService;
import cars._security.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static cars._security.controller.constants.AuthApi.USER;
import static cars.controller.constants.CarsApi.*;

@Configuration
public class SecurityConfig {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;

    public SecurityConfig(UserService userService, JwtTokenUtils jwtTokenUtils) {
        this.userService = userService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(configurer -> configurer.configurationSource(corsConfigurationSource()))
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(consumer -> consumer
                        // authentication
                        .requestMatchers(HttpMethod.POST, AuthApi.AUTH + AuthApi.REGISTRATION).permitAll()
                        .requestMatchers(HttpMethod.POST, AuthApi.AUTH + AuthApi.LOGIN).permitAll()
                        .requestMatchers(USER + "/**").hasRole("ADMIN")
                        .requestMatchers(SHUTDOWN).hasRole("ADMIN")
                        //  rent cars company
                        .requestMatchers(CAR + "/**", MODEL + "/**", GET_ALL_CARS).hasRole("ADMIN")
                        .requestMatchers(MODEL, CAR, REMOVE_CAR + "/**", CLEAR_CARS + "/**", GET_PROFIT_MODEL + "/**")
                        .hasRole("ADMIN")
                        .requestMatchers(DRIVER, CAR_RENT, CAR_RETURN, GET_ALL_DRIVERS, DRIVER + "/**", GET_ALL_RECORDS)
                        .hasRole("ADMIN")
                        .requestMatchers(GET_DRIVER_CARS +"/**", GET_CAR_DRIVERS +"/**").hasRole("ADMIN")
                        .requestMatchers(MOST_POPULAR_MODELS, MOST_PROFIT_MODELS, GET_ALL_MODEL_NAMES, GET_ALL_MODELS).hasRole("ADMIN")
                )
                .sessionManagement(customer -> customer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(new AuthenticationFilter(this.jwtTokenUtils), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        var corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedOriginPatterns(List.of("*"));
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsConfigurationSource;

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }

//    private AuthenticationEntryPoint unauthorizedResponse() {
//        return (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//    }

}