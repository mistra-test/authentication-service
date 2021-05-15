package com.example.authenticationservice.controller;

import com.example.authenticationservice.models.AuthRequest;
import com.example.authenticationservice.models.AuthResponse;
import com.example.authenticationservice.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired private AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthResponse generateToken(@RequestBody AuthRequest authRequest)
            throws BadCredentialsException {

        return new AuthResponse(authenticationService.jwtFromAuthRequest(authRequest));
    }
}
