package com.moms.app.persistence.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN,
    CREATOR,
    USER;


    @Override
    public String getAuthority() {
        return name();
    }
}
