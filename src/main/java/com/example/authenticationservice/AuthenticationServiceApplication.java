package com.example.authenticationservice;

import com.example.authenticationservice.models.GrantListWrapper;
import com.example.authenticationservice.models.UserDTO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.web.client.RestTemplate;

@TypeHint(types = UserDTO.class)
@TypeHint(types = GrantListWrapper.class)
@NativeHint(trigger = RestTemplate.class, options = "--enable-url-protocols=http")
@SpringBootApplication
public class AuthenticationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
