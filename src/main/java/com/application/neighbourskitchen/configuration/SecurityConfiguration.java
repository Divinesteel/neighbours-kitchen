package com.application.neighbourskitchen.configuration;

import com.application.neighbourskitchen.controller.DatabaseUserDetailPasswordService;
import com.application.neighbourskitchen.controller.DatabaseUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DatabaseUserDetailsService databaseUserDetailsService;
    private final DatabaseUserDetailPasswordService databaseUserDetailPasswordService;

    public SecurityConfiguration(DatabaseUserDetailsService databaseUserDetailsService, DatabaseUserDetailPasswordService databaseUserDetailPasswordService) {
        this.databaseUserDetailsService = databaseUserDetailsService;
        this.databaseUserDetailPasswordService = databaseUserDetailPasswordService;
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsPasswordService(this.databaseUserDetailPasswordService));
        provider.setUserDetailsService(this.databaseUserDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return new BCryptPasswordEncoder(bcCryptWorkFactorService.calculateStrength());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/registration")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }
}
