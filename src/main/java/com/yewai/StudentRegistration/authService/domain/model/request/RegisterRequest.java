package com.yewai.StudentRegistration.authService.domain.model.request;

import com.yewai.StudentRegistration.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterRequest {
    @NotEmpty(message = "UserName is required field.")
    @Size(max = 255)
    private String name;

    @NotEmpty(message = "Password is required field.")
    @Size(min = 8, max = 255)
    private String password;
    
    private Role role;
}
