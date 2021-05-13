package com.example.authenticationservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id private Long id;

    private String name;
    private String password;
    private String role;
}
