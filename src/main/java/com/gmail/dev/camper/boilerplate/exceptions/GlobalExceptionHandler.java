package com.gmail.dev.camper.boilerplate.exceptions;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  // Custom Exceptions
  //

  @ExceptionHandler(AuthenticationException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorResponse handleAuthenticationExceptions(Exception exception, WebRequest request) {
    Map<String, String> exceptionMessages = new HashMap<>();
    exceptionMessages.put("errorMessage", exception.getMessage());
    return new ErrorResponse(
        new Date(),
        HttpStatus.UNAUTHORIZED.value(),
        HttpStatus.UNAUTHORIZED.getReasonPhrase(),
        exceptionMessages);
  }

  @ExceptionHandler(AccessDeniedException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ErrorResponse handleDeniedExceptions(Exception exception, WebRequest request) {
    Map<String, String> exceptionMessages = new HashMap<>();
    exceptionMessages.put("errorMessage", exception.getMessage());
    return new ErrorResponse(
        new Date(),
        HttpStatus.FORBIDDEN.value(),
        HttpStatus.FORBIDDEN.getReasonPhrase(),
        exceptionMessages);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> exceptionMessages = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              exceptionMessages.put(fieldName, errorMessage);
            });

    return new ErrorResponse(
        new Date(),
        HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        exceptionMessages);
  }

  // Global Exception Handler
  @ExceptionHandler(Exception.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleGlobalException(Exception exception, WebRequest request) {
    Map<String, String> exceptionMessages = new HashMap<>();
    exceptionMessages.put("errorMessage", exception.getMessage());

    return new ErrorResponse(
        new Date(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
        exceptionMessages);
  }
}
