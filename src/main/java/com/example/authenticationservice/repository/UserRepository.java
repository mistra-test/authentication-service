package com.example.authenticationservice.repository;

import com.example.authenticationservice.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
    Optional<User> findByName(String name);
}
