package com.example.mse.itsec.demonstrationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN") // Role ADMIN may access all end points under admin
                .antMatchers("/").hasAnyRole("USER","ADMIN") // Any of the roles USER or ADMIN may access
                .antMatchers("/login*").permitAll() // everybody my access the login page
                .antMatchers("/greeting*").permitAll() // everybody may access the greeting end point.
                .anyRequest().authenticated() // all other request need authentication
                .and()
                .formLogin() // Tell Spring to use form login authentication
                .loginPage("/login") // Supply the login page
                .loginProcessingUrl("/perform_login") // Endpoint to use for login
                .defaultSuccessUrl("/protected", true) // where to go after login
                .failureUrl("/login.html?error=true")
                .and()
                .logout()
                .logoutUrl("/perform_logout") // end point to call for logout
                .deleteCookies("JSESSIONID") // delete the session cookie after logout
                .logoutSuccessUrl("/login");
    }

    // Register some test users
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
    }

    // Specify the encoder to use for passwords
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new Argon2PasswordEncoder();
    }
}
