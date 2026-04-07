package com.gmail.dev.camper.boilerplate.security;

import com.gmail.dev.camper.boilerplate.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// 1. Security Config class
@Configuration
@EnableMethodSecurity // enable method level
public class SecurityConfig {

  // 2. Add Authentication Manager
  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
  }

  // 3. Password Encounder
  // Need this because spring does not allow
  // not encrypted password (raw)
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter();
  }

  // 4: filter Chain
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    return httpSecurity
        .csrf(
            csrc ->
                csrc.disable()) // need to disable this so that we can authenticate Post, Delete etc
        .authorizeHttpRequests(
            authorize -> {
              authorize.requestMatchers(HttpMethod.POST, "/api/user").permitAll();
              authorize.requestMatchers("/login").permitAll();

              //
              authorize.anyRequest().authenticated();
            })
        // 5. Add basic auth filter
        .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .build();

    // 6. implement custom userdetails service
    // check the /service/CustomUserDetailsService
  }
}
