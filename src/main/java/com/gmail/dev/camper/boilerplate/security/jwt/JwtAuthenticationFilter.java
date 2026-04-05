package com.gmail.dev.camper.boilerplate.security.jwt;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
             
           // Header
           // Authorization Bearer 

           // 1. get the header bearer
           String authHeader = request.getHeader("Authorization");
           
           // 2. validate if exist / malform
           if(authHeader != null && authHeader.startsWith("Bearer ")) {

                String bearerToken = authHeader.substring(7).trim();

                // 4. valid token
                if(JwtUtils.validateToken(bearerToken)) {
                // 5. autheticate
                    String name = JwtUtils.getClaims(bearerToken).getSubject();
                    Authentication authenticate = new UsernamePasswordAuthenticationToken(name, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authenticate);
                }
            }
            filterChain.doFilter(request, response);
    }


}


