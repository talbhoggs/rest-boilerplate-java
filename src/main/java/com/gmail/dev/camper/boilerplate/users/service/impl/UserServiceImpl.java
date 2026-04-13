package com.gmail.dev.camper.boilerplate.users.service.impl;

import com.gmail.dev.camper.boilerplate.common.response.HttpResponse;
import com.gmail.dev.camper.boilerplate.users.dto.UserDto;
import com.gmail.dev.camper.boilerplate.users.entity.Role;
import com.gmail.dev.camper.boilerplate.users.entity.User;
import com.gmail.dev.camper.boilerplate.users.repository.RoleRepository;
import com.gmail.dev.camper.boilerplate.users.repository.UserRepository;
import com.gmail.dev.camper.boilerplate.users.service.UserService;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final RoleRepository roleRepository;

  @Override
  public ResponseEntity<HttpResponse> register(UserDto userDto) {

    Optional<User> existingUser = userRepository.findByUsername(userDto.getUsername());

    if (existingUser.isPresent()) {
      throw new RuntimeException("Username already taken");
    }

    User newUser = new User();
    newUser.setEmail(userDto.getEmail());
    newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
    newUser.setUsername(userDto.getUsername());
    newUser.setRoles(getDefaultRoles(userDto));
    userRepository.save(newUser);

    return ResponseEntity.accepted()
        .body(
            new HttpResponse(
                new Date(),
                HttpStatus.ACCEPTED.value(),
                "Registeration completed successfully",
                userDto));
  }

  @Override
  public ResponseEntity<HttpResponse> save(UserDto userDto) {

    Optional<User> existingUser = userRepository.findByUsername(userDto.getUsername());

    if (existingUser.isPresent()) {
      throw new RuntimeException("Username already taken");
    }

    User newUser = new User();
    newUser.setEmail(userDto.getEmail());
    newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
    newUser.setUsername(userDto.getUsername());
    newUser.setRoles(getRoles(userDto));
    userRepository.save(newUser);

    return ResponseEntity.accepted()
        .body(
            new HttpResponse(
                new Date(), HttpStatus.ACCEPTED.value(), "User save successfully", userDto));
  }

  private Set<Role> getDefaultRoles(UserDto userDto) {

    Set<Role> roles = new HashSet<>();
    Optional<Role> defaultRole = roleRepository.findByName("ROLE_USER");

    if (!defaultRole.isPresent()) {
      throw new RuntimeException("Unable to add the default role.");
    }

    userDto.getRoles().clear(); // prevent user from adding unwanted roles

    userDto.getRoles().add(defaultRole.get().getName());
    roles.add(defaultRole.get());
    return roles;
  }

  private Set<Role> getRoles(UserDto userDto) {

    Set<Role> roles = new HashSet<>();
    Set<String> rolesName = userDto.getRoles();
    List<Role> rolesList = roleRepository.findAll();
    List<String> roleListName = rolesList.stream().map(map -> map.getName()).toList();

    if (!roleListName.containsAll(rolesName)) {
      throw new RuntimeException("In valid roles");
    }

    roles.addAll(rolesList);

    return roles;
  }
}
