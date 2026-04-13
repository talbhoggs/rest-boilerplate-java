package com.gmail.dev.camper.boilerplate.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class UserDto {
  private long id;

  @NotBlank(message = "username must not be blank") @Size(min = 3, max = 20, message = "username must be between 3 and 20 characters") private String username;

  @NotBlank(message = "password must not be blank") @Size(min = 3, max = 20, message = "password must be between 3 and 20 characters") private String password;

  @NotBlank @Email private final String email;

  private Set<String> roles = new HashSet<>();
}
