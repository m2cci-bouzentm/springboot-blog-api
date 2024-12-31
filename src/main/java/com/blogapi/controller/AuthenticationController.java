package com.blogapi.controller;


import com.blogapi.dto.UserDTO;
import com.blogapi.entity.CustomUserDetailsImpl;
import com.blogapi.entity.User;
import com.blogapi.response.LoginResponse;
import com.blogapi.service.AuthenticationService;
import com.blogapi.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RestController
@RequestMapping("/")
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;


    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserDTO userDto) {
        try {
            User registeredUser = authenticationService.signup(userDto);
            return getLoginResponse(userDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserDTO userDto) {
        return getLoginResponse(userDto);
    }



    private ResponseEntity<LoginResponse> getLoginResponse(@RequestBody UserDTO userDto) {
        User authenticatedUser = authenticationService.authenticate(userDto);

        CustomUserDetailsImpl userDetails = new CustomUserDetailsImpl(
                authenticatedUser.getId(),
                authenticatedUser.getUsername(),
                authenticatedUser.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(authenticatedUser.getRole().name()))
        );

        String jwtToken = jwtService.generateToken(userDetails);

        LoginResponse loginResponse = new LoginResponse()
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setUser(userDto);
        loginResponse.getUser().setId(userDetails.getId());
        loginResponse.getUser().setPassword(null);

        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }
}