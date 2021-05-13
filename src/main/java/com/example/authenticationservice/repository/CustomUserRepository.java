package com.example.authenticationservice.repository;

import com.example.authenticationservice.models.User;

import java.util.Optional;

public interface CustomUserRepository {
    Optional<User> findByName(String name);
}
