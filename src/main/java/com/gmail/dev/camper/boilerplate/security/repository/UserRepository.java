package com.gmail.dev.camper.boilerplate.security.repository;

import com.gmail.dev.camper.boilerplate.security.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);
}
