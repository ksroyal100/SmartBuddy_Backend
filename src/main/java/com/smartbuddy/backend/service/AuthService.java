package com.smartbuddy.backend.service;

import com.smartbuddy.backend.dto.AuthResponse;
import com.smartbuddy.backend.dto.LoginRequest;
import com.smartbuddy.backend.dto.RegisterRequest;
import com.smartbuddy.backend.entity.User;

public interface AuthService {

	User register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
