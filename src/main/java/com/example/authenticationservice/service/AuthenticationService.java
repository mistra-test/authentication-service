package com.example.authenticationservice.service;

import com.example.authenticationservice.exception.NullWrapperException;
import com.example.authenticationservice.exception.UserNotFoundException;
import com.example.authenticationservice.models.AuthRequest;
import com.example.authenticationservice.models.GrantListWrapper;
import com.example.authenticationservice.models.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {
    @Value("${com.example.user-resource.location}")
    private String userResourceEndpoint;

    @Value("${com.example.jwt-resource.location}")
    private String jwtResourceEndpoint;

    @Value("${com.example.acl-resource.location}")
    private String aclResourceEndpoint;

    @Autowired RestTemplate restTemplate;

    public String jwtFromAuthRequest(AuthRequest authRequest) {
        // log.info(aclResourceEndpoint);

        var user = fetchUser(authRequest);

        var grantListWrapper = fetchGrantListWrapper(user.getId());

        var userDetail = new User();
        userDetail.setName(String.valueOf(user.getId()));
        userDetail.setPassword(user.getPassword());
        userDetail.setRoles(grantListWrapper.getGrantList());

        return restTemplate.postForObject(
                jwtResourceEndpoint + "/jwt/encode", userDetail, String.class);
    }

    private UserDTO fetchUser(AuthRequest authRequest) {
        var user =
                restTemplate.postForObject(
                        userResourceEndpoint + "/user/authenticate", authRequest, UserDTO.class);
        if (user == null) {
            var message =
                    String.format(
                            "user not found with email %s and password %s",
                            authRequest.getEmail(), authRequest.getPassword());
            // log.error(message);
            throw new UserNotFoundException(message);
        }
        return user;
    }

    private GrantListWrapper fetchGrantListWrapper(Long userId) {
        var grantListWrapper =
                restTemplate.getForObject(
                        aclResourceEndpoint + "/acl/grant/findbyuser/" + userId,
                        GrantListWrapper.class);
        if (grantListWrapper == null) {
            var message = String.format("grantListWrapper was empty for user with id %d", userId);
            // log.error(message);
            throw new NullWrapperException(message);
        }
        return grantListWrapper;
    }
}
