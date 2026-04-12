package com.gmail.dev.camper.boilerplate.security.controller;

import com.gmail.dev.camper.boilerplate.common.response.HttpResponse;
import com.gmail.dev.camper.boilerplate.security.jwt.JwtUtils;
import com.gmail.dev.camper.boilerplate.users.dto.LoginDto;
import com.gmail.dev.camper.boilerplate.users.dto.UserDto;
import com.gmail.dev.camper.boilerplate.users.service.UserService;
import jakarta.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<HttpResponse> login(@Valid @RequestBody LoginDto user) {

    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

    Authentication authentication = authenticationManager.authenticate(token);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt =
        JwtUtils.generateToken(
            (org.springframework.security.core.userdetails.User) authentication.getPrincipal());
    Map<String, String> jwtMap = new HashMap<>();
    jwtMap.put("accessToken", jwt);
    HttpResponse respose =
        new HttpResponse(new Date(), HttpStatus.OK.value(), "Login successful", jwtMap);
    return ResponseEntity.ok(respose);
  }

  @PostMapping("/register")
  public ResponseEntity<HttpResponse> register(@Valid @RequestBody UserDto userDto) {
    return userService.register(userDto);
  }
}
