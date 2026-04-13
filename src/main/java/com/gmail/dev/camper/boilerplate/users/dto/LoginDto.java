package com.gmail.dev.camper.boilerplate.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginDto {

  @NotBlank(message = "username must not be blank") @Size(min = 3, max = 20, message = "username must be between 3 and 20 characters") private final String username;

  @NotBlank(message = "password must not be blank") @Size(min = 3, max = 15, message = "password must be between 3 and 20 characters") private final String password;
}
