package com.gmail.dev.camper.boilerplate.users.service;

import com.gmail.dev.camper.boilerplate.common.response.HttpResponse;
import com.gmail.dev.camper.boilerplate.users.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

  public ResponseEntity<HttpResponse> save(UserDto user);

  public ResponseEntity<HttpResponse> register(UserDto user);
}
