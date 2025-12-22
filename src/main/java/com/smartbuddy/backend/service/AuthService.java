package com.smartbuddy.backend.service;

import com.smartbuddy.backend.dto.AuthResponse;
import com.smartbuddy.backend.dto.LoginRequest;
import com.smartbuddy.backend.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
