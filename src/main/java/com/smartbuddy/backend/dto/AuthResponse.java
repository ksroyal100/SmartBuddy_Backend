package com.smartbuddy.backend.dto;

public class AuthResponse {

    private String token;
    private String username;

    public AuthResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public AuthResponse(String token2) {
	
	}

	public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }
}
