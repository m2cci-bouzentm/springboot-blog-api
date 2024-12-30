package com.blogapi.service;


import com.blogapi.dao.UserDAO;
import com.blogapi.dto.UserDTO;
import com.blogapi.entity.Role;
import com.blogapi.entity.User;
import com.blogapi.response.UniqueConstraintViolationException;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserDAO userDAO;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(
            UserDAO userDAO,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(UserDTO input) {

        if (input.getUsername() == null || input.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (input.getEmail() == null || input.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        User user = new User();

        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

//        TODO set roles dynamically - current role default to USER
//        user.setRole(Role.ROLE_USER);
//        user.setRole(Role.ROLE_AUTHOR);

        try {
            userDAO.saveUser(user);
            return user;
        } catch (UniqueConstraintViolationException e) {
            throw new UniqueConstraintViolationException(e);
        }


    }

    public User authenticate(UserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userDAO.findByUsername(input.getUsername())
                .orElseThrow();
    }
}
