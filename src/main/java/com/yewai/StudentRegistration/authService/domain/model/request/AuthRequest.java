package com.yewai.StudentRegistration.authService.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequest {

    @NotEmpty(message = "UserName is required field.")
    @Size(max = 255)
    private String name;

    @NotEmpty(message = "Password is required field.")
    @Size(min = 8, max = 255)
    private String password;
}
