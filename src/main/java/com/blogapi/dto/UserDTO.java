package com.blogapi.dto;

import com.blogapi.entity.Role;
import com.blogapi.entity.User;
import jakarta.persistence.Column;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class UserDTO {
    private String id;

    private String username;

    private String email;

    private String password;
    private String passwordConfirmation;
    private String authorKey;

    private Role role;

    private String avatarUrl;


    public UserDTO() {

    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password= user.getPassword();
        this.email= user.getEmail();
        this.role = user.getRole();
        this.avatarUrl = user.getAvatarUrl();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getAuthorKey() {
        return authorKey;
    }

    public void setAuthorKey(String authorKey) {
        this.authorKey = authorKey;
    }
}
