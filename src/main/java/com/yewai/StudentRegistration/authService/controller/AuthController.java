package com.yewai.StudentRegistration.authService.controller;

import com.yewai.StudentRegistration.authService.domain.model.dto.AuthDTO;
import com.yewai.StudentRegistration.authService.domain.model.dto.UserDTO;
import com.yewai.StudentRegistration.authService.domain.model.request.AuthRequest;
import com.yewai.StudentRegistration.authService.domain.model.request.RegisterRequest;
import com.yewai.StudentRegistration.authService.domain.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(
            @Valid @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(
            @Valid @RequestBody AuthRequest authRequest
    ) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthDTO> refreshToken(
            HttpServletRequest request
    ) throws IOException {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("this is user");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("this is admin");
    }
}
