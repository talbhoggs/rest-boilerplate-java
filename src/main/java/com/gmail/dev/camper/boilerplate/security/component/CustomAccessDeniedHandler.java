package com.gmail.dev.camper.boilerplate.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.dev.camper.boilerplate.exceptions.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException {

    Map<String, String> exceptionMessages = new HashMap<>();
    exceptionMessages.put("errorMessage", accessDeniedException.getMessage());

    ErrorResponse errorResponse =
        new ErrorResponse(
            new Date(),
            HttpStatus.FORBIDDEN.value(),
            HttpStatus.FORBIDDEN.getReasonPhrase(),
            exceptionMessages);

    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.setContentType("application/json");

    new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
  }
}
