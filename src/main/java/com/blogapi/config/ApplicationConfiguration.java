package com.blogapi.config;


import com.blogapi.dao.UserDAO;
import com.blogapi.entity.CustomUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@Configuration
public class ApplicationConfiguration {

    /*
    * userDetailsService() defines how to retrieve the user using the UserRepository that is injected
    * passwordEncoder() creates an instance of the BCryptPasswordEncoder() used to encode the plain user password
    * authenticationProvider() sets the new strategy to perform the authentication.
    * AuthenticationManager is the main strategy interface for authentication
     *  */

    private final UserDAO userDAO;

    @Autowired
    public ApplicationConfiguration(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            com.blogapi.entity.User user = userDAO.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            String roleName = user.getRole().name();

            return new CustomUserDetailsImpl(
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(roleName))
            );
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());


        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
