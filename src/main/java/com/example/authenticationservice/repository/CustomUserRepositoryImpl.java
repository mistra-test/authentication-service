package com.example.authenticationservice.repository;

import com.example.authenticationservice.models.User;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

    @PersistenceContext EntityManager em;

    @Override
    public Optional<User> findByName(String name) {
        return em
                .createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst();
    }
}
