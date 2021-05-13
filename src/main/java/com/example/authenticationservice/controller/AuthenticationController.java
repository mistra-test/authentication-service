package com.example.authenticationservice.controller;

import com.example.authenticationservice.models.AuthRequest;
import com.example.authenticationservice.models.AuthResponse;
import com.example.authenticationservice.models.User;
import com.example.authenticationservice.security.CurrentUserDetailsService;
import com.example.authenticationservice.security.JwtUtils;
import com.example.authenticationservice.service.AuthenticationService;
import com.example.authenticationservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired private AuthenticationManager authenticationManager;

    @Autowired private UserDetailsService userDetailsService;

    @Autowired private AuthenticationService authenticationService;

    @Autowired private CurrentUserDetailsService currentUserDetailsService;

    @Autowired private UserService userService;

    @Autowired private JwtUtils jwtUtils;

    @GetMapping("/user/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/login")
    public AuthResponse generateToken(@RequestBody AuthRequest authRequest)
            throws BadCredentialsException {

        return new AuthResponse(authenticationService.jwtFromAuthRequest(authRequest));
    }

    @GetMapping("/jwttoprincipal/{token}")
    public UserDetails jwtToPrincipal(@PathVariable String token) {
        String userName = jwtUtils.extractUserName(token);
        return currentUserDetailsService.loadUserByUsername(userName);
    }
}
