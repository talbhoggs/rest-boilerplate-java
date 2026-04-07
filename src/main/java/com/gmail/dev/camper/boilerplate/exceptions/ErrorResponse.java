package com.gmail.dev.camper.boilerplate.exceptions;

import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class ErrorResponse {
  private final Date timestamp;
  private final int status;
  private final String error;
  private final String message;
}
