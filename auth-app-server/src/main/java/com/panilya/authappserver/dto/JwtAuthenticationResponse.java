package com.panilya.authappserver.dto;

public class JwtAuthenticationResponse {

    private String token;
    private final String tokenType = "Bearer";

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }
}
