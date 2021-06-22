package com.example.authenticationservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Long id;

    private String firstName;
    private String lastName;

    private String email;
    private String password;
}
