package com.blogapi.entity;

public enum Role {
    ROLE_AUTHOR,
    ROLE_USER;

    @Override
    public String toString() {
        return name();
    }
}
