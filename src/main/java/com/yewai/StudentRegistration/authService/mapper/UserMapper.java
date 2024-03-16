package com.yewai.StudentRegistration.authService.mapper;

import com.yewai.StudentRegistration.authService.domain.model.dto.UserDTO;
import com.yewai.StudentRegistration.authService.domain.model.entity.User;
import com.yewai.StudentRegistration.authService.domain.model.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }

    public User toEntity(RegisterRequest user) {
        return User.builder()
                .name(user.getName())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole())
                .build();
    }
}
