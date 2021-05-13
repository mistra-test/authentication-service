package com.example.authenticationservice.models;

import lombok.AllArgsConstructor;

import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class CustomAuthority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
