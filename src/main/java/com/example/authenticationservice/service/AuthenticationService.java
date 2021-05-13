package com.example.authenticationservice.service;

import com.example.authenticationservice.models.AuthRequest;
import com.example.authenticationservice.models.UserDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
class GrantListWrapper implements Serializable {
    private List<String> grantList;
}

@Log4j2
@Service
public class AuthenticationService {

    @Autowired RestTemplate restTemplate;

    public String jwtFromAuthRequest(AuthRequest authRequest) {
        try {
            var user =
                    restTemplate.postForObject(
                            "http://user-resource/user/authenticate", authRequest, UserDTO.class);

            var grantListWrapper =
                    restTemplate.getForObject(
                            "http://acl-resource/acl/grant/findbyuser/" + user.getId(),
                            GrantListWrapper.class);

            var userDetail = new User();
            userDetail.setName(String.valueOf(user.getId()));
            userDetail.setPassword(user.getPassword());
            userDetail.setRoles(grantListWrapper.getGrantList());

            return restTemplate.postForObject(
                    "http://jwt-resource/jwt/encode", userDetail, String.class);

        } catch (Exception e) {
            log.error("errore a leggere", e);
            // TODO: qualcosa di più sensato
            return null;
        }
        // da JWT restituisci il jwt
    }
}
