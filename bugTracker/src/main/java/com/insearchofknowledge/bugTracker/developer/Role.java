package com.insearchofknowledge.bugTracker.developer;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_DEVELOPER, ROLE_PROJECT_LEADER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
