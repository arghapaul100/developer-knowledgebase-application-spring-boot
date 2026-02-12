package com.example.knowledgebase.service.impl;

import com.example.knowledgebase.dto.AuthDtos;
import com.example.knowledgebase.entity.User;
import com.example.knowledgebase.enums.Role;
import com.example.knowledgebase.exception.BadRequestException;
import com.example.knowledgebase.repository.UserRepository;
import com.example.knowledgebase.security.JwtService;
import com.example.knowledgebase.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthDtos.AuthResponse register(AuthDtos.RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new BadRequestException("Username already in use");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new BadRequestException("Email already in use");
        }

        User user = userRepository.save(User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build());

        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());
        return new AuthDtos.AuthResponse(token, user.getId(), user.getUsername(), user.getRole());
    }

    @Override
    public AuthDtos.AuthResponse login(AuthDtos.LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());
        return new AuthDtos.AuthResponse(token, user.getId(), user.getUsername(), user.getRole());
    }
}
