package com.gmail.dev.camper.boilerplate.security.controller;

import com.gmail.dev.camper.boilerplate.security.entity.User;
import com.gmail.dev.camper.boilerplate.security.jwt.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private final AuthenticationManager authenticationManager;

  public AuthController(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody User user) {
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
    Authentication authentication = authenticationManager.authenticate(token);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt =
        JwtUtils.generateToken(
            (org.springframework.security.core.userdetails.User) authentication.getPrincipal());
    return ResponseEntity.ok(jwt);
  }
}
