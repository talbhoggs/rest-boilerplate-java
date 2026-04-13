package com.gmail.dev.camper.boilerplate.common.response;

import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class HttpResponse {

  private final Date timestamp;
  private final int status;
  private final String message;
  private final Object data;
}
