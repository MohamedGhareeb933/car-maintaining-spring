package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * allow remove driver - add or update for admin only
     * allow the get method for user and admin
     * disable csrf - use http basic for basic http methods .
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/remove").hasRole("ADMIN")
                .antMatchers("/add").hasRole("ADMIN")
                .antMatchers("/update").hasRole("ADMIN")
                .antMatchers("/").hasAnyRole("ADMIN, USER")
                .and().httpBasic().and().csrf().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() { return new BCryptPasswordEncoder(); }
}
