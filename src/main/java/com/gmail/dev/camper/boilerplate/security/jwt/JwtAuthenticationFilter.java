package com.gmail.dev.camper.boilerplate.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    Optional<String> bearerToken = getBearerToken(request);
    if (bearerToken.isPresent() && JwtUtils.validateToken(bearerToken.get())) {
      authenticateUser(bearerToken.get());
    }
    filterChain.doFilter(request, response);
  }

  private Optional<String> getAuthHeader(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader("Authorization"));
  }

  private void authenticateUser(String bearerToken) {
    String name = JwtUtils.getClaims(bearerToken).getSubject();

    List<String> roles = JwtUtils.getClaims(bearerToken).get("roles", List.class);

    List<GrantedAuthority> authorities =
        roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    Authentication authenticate = new UsernamePasswordAuthenticationToken(name, null, authorities);
    SecurityContextHolder.getContext().setAuthentication(authenticate);
  }

  private Optional<String> getBearerToken(HttpServletRequest request) {
    return getAuthHeader(request)
        .filter(header -> header.toLowerCase().startsWith("bearer "))
        .map(header -> header.substring(7).trim());
  }
}
