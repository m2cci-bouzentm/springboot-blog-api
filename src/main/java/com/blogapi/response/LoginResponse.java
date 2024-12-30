package com.blogapi.response;

import com.blogapi.dto.UserDTO;
import com.blogapi.entity.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class LoginResponse {
    private String token;

    private long expiresIn;

    private User user;

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
