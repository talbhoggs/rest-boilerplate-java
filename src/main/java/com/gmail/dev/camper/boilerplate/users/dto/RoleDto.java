package com.gmail.dev.camper.boilerplate.users.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class RoleDto {
  private final int id;
  private final String name;
}
