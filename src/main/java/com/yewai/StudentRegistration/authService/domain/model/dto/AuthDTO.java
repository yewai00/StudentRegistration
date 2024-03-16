package com.yewai.StudentRegistration.authService.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthDTO(
        @JsonProperty("access_token")
        String accessToken,

        @JsonProperty("refresh_token")
        String refreshToken
    ) {}
