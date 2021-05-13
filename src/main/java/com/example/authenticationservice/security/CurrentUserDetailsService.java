package com.example.authenticationservice.security;

import com.example.authenticationservice.models.CustomUser;
import com.example.authenticationservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository
                .findByName(username)
                .map(CustomUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
