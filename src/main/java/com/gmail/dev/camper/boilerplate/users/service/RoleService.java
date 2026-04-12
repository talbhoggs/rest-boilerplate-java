package com.gmail.dev.camper.boilerplate.users.service;

import com.gmail.dev.camper.boilerplate.users.dto.RoleDto;

public interface RoleService {
  RoleDto getByName(String name);
}
