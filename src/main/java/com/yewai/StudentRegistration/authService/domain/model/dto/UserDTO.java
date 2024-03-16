package com.yewai.StudentRegistration.authService.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yewai.StudentRegistration.enums.Role;

public record UserDTO(
        Long id,
        String name,
        Role role
    ) {}
