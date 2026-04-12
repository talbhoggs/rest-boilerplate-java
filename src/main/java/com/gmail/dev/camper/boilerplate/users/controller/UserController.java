package com.gmail.dev.camper.boilerplate.users.controller;

import com.gmail.dev.camper.boilerplate.users.entity.User;
import com.gmail.dev.camper.boilerplate.users.repository.UserRepository;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final PasswordEncoder encoder;
  private final UserRepository userRepository;

  public UserController(PasswordEncoder encoder, UserRepository userRepository) {
    this.encoder = encoder;
    this.userRepository = userRepository;
  }

  @PostMapping
  public ResponseEntity<String> createUser(@RequestBody User user) {

    // refactor code
    // error handling
    //
    Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

    if (!existingUser.isPresent()) {
      User newUser = new User();
      newUser.setUsername(user.getUsername());
      newUser.setPassword(encoder.encode(user.getPassword()));
      userRepository.save(newUser);
      return ResponseEntity.ok("User Created Successfully");
    }

    return ResponseEntity.badRequest().body("User Created Successfully");
  }
}
