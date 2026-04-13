package com.gmail.dev.camper.boilerplate.security.service;

import com.gmail.dev.camper.boilerplate.users.entity.User;
import com.gmail.dev.camper.boilerplate.users.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<User> existingUser = userRepository.findByUsername(username);

    if (!existingUser.isPresent()) {
      throw new UsernameNotFoundException("Invalid or User not found");
    }

    List<SimpleGrantedAuthority> authorities =
        existingUser.get().getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .toList();

    return org.springframework.security.core.userdetails.User.builder()
        .username(existingUser.get().getUsername())
        .password(existingUser.get().getPassword())
        .authorities(authorities)
        .build();
  }
}
