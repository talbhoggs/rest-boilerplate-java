package com.gmail.dev.camper.boilerplate.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.dev.camper.boilerplate.exceptions.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {

    Map<String, String> exceptionMessages = new HashMap<>();
    exceptionMessages.put("errorMessage", authException.getMessage());

    ErrorResponse errorResponse =
        new ErrorResponse(
            new Date(),
            HttpStatus.UNAUTHORIZED.value(),
            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
            exceptionMessages);

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json");

    new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
  }
}
