package com.smartbuddy.backend.controller;

import com.smartbuddy.backend.dto.AuthResponse;
import com.smartbuddy.backend.dto.LoginRequest;
import com.smartbuddy.backend.dto.RegisterRequest;
import com.smartbuddy.backend.entity.User;
import com.smartbuddy.backend.security.JwtUtil;
import com.smartbuddy.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")

public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(
            AuthService authService,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager
    ) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(request.getUsername());

        return ResponseEntity.ok(
                new AuthResponse(token, request.getUsername())
        );
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ) {
        // 1️⃣ Create user
        User user = authService.register(request);

        // 2️⃣ Generate JWT
        String token = jwtUtil.generateToken(user.getUsername());

        // 3️⃣ Return JSON
        return ResponseEntity.ok(
                new AuthResponse(token, user.getUsername())
        );
    }
}
