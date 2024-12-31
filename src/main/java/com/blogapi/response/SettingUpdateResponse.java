
package com.blogapi.response;

import com.blogapi.dto.UserDTO;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class SettingUpdateResponse {
    private String token;

    private long expiresIn;

    private UserDTO user;

    public String getToken() {
        return token;
    }

    public SettingUpdateResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public SettingUpdateResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}

