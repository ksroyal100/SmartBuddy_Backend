package com.smartbuddy.backend.service.impl;

import com.smartbuddy.backend.dto.AuthResponse;
import com.smartbuddy.backend.dto.LoginRequest;
import com.smartbuddy.backend.dto.RegisterRequest;
import com.smartbuddy.backend.entity.User;
import com.smartbuddy.backend.repository.UserRepository;
import com.smartbuddy.backend.security.JwtUtil;
import com.smartbuddy.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

   @Override
public void register(RegisterRequest request) {

    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));

    // ✅ DEFAULT ROLE
    user.setRole("ROLE_USER");

    userRepository.save(user);
}


    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(request.getUsername());

        return new AuthResponse(token);
    }
}
