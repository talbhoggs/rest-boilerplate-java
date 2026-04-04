package com.gmail.dev.camper.boilerplate.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gmail.dev.camper.boilerplate.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    
}
