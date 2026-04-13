package com.gmail.dev.camper.boilerplate.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class JwtUtils {

  private static String secretKey = "p71KY78zu/IsOh7dq+XkSGmPuFacAmiH6KTUcDZ0DKA=";

  public static String generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put(
        "roles",
        user.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()));
    return Jwts.builder()
        .subject(user.getUsername())
        .claims(claims)
        .expiration(new Date(System.currentTimeMillis() + 900000))
        .signWith(signedKeys())
        .compact();
  }

  public static Claims getClaims(String token) {
    return Jwts.parser().verifyWith(signedKeys()).build().parseSignedClaims(token).getPayload();
  }

  public static boolean validateToken(String token) {
    try {
      return getClaims(token).getExpiration().after(new Date());
    } catch (Exception ex) {
      return false;
    }
  }

  private static SecretKey signedKeys() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
  }
}
