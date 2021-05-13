package com.example.authenticationservice.service;

import com.example.authenticationservice.models.User;
import com.example.authenticationservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    @Cacheable("users")
    public Optional<User> findByUsername(String username) {
        return userRepository.findByName(username);
    }
}
