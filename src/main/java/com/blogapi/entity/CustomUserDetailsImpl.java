package com.blogapi.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetailsImpl implements UserDetails {
    private final String id;
    private final String username;
    private final String password;
    private final boolean credentialsNonExpired;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetailsImpl(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.credentialsNonExpired = true;
    }


    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


    @Override
    public String toString() {
        return "CustomUserDetailsImpl{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", authorities=" + authorities +
                '}';
    }
}
