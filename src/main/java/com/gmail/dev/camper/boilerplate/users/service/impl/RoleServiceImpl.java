package com.gmail.dev.camper.boilerplate.users.service.impl;

import com.gmail.dev.camper.boilerplate.users.dto.RoleDto;
import com.gmail.dev.camper.boilerplate.users.entity.Role;
import com.gmail.dev.camper.boilerplate.users.repository.RoleRepository;
import com.gmail.dev.camper.boilerplate.users.service.RoleService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository repository;

  @Override
  public RoleDto getByName(String name) {

    Optional<Role> role = repository.findByName(name);

    if (!role.isPresent()) {
      throw new RuntimeException("No role found");
    }

    return entityToDto(role.get());
  }

  private Role dtoToEntity(Role role, RoleDto dto) {
    role.setId(dto.getId());
    role.setName(dto.getName());
    return role;
  }

  private RoleDto entityToDto(Role role) {
    return new RoleDto(role.getId(), role.getName());
  }
}
