package com.example.knowledgebase.service;

import com.example.knowledgebase.dto.AuthDtos;

public interface AuthService {
    AuthDtos.AuthResponse register(AuthDtos.RegisterRequest request);
    AuthDtos.AuthResponse login(AuthDtos.LoginRequest request);
}
