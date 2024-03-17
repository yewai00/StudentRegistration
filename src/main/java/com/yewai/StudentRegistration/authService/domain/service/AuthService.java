package com.yewai.StudentRegistration.authService.domain.service;

import com.yewai.StudentRegistration.authService.domain.model.dto.AuthDTO;
import com.yewai.StudentRegistration.authService.domain.model.request.RegisterRequest;
import com.yewai.StudentRegistration.authService.domain.repository.UserRepository;
import com.yewai.StudentRegistration.config.JwtTokenProvider;
import com.yewai.StudentRegistration.authService.domain.model.dto.UserDTO;
import com.yewai.StudentRegistration.authService.domain.model.request.AuthRequest;
import com.yewai.StudentRegistration.authService.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class AuthService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserMapper userMapper;

    public UserDTO register(RegisterRequest registerRequest) {
        var user = userMapper.toEntity(registerRequest);
        var storedUser = this.userRepository.save(user);
        return userMapper.toDTO(storedUser);
    }

    public AuthDTO login(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getName(),
                            authRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException e) {
            throw e;
        }

        var user = userRepository.findByName(authRequest.getName())
                .orElseThrow();
        var jwtToken = jwtTokenProvider.generateToken(
                Map.of("role",user.getRole()),
                user
            );
        var refreshToken = jwtTokenProvider.generateRefreshToken(user);
        return new AuthDTO(jwtToken, refreshToken);
    }

    public AuthDTO refreshToken(
            HttpServletRequest request
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userName;

        refreshToken = authHeader.substring(7);
        userName = jwtTokenProvider.extractUserName(refreshToken);
        var userDetails = this.userRepository.findByName(userName)
                .orElseThrow();
        var accessToken = jwtTokenProvider.generateToken(
                Map.of("role",userDetails.getRole()),
                userDetails
            );
        return new AuthDTO(accessToken, refreshToken);
    }
}
