package com.example.authenticationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUser(User user) {
        this.id = user.getId();
        this.username = user.getName();
        this.password = user.getPassword();

        authorities =
                Stream.of(user.getRole())
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
