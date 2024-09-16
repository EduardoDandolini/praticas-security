package com.implementation.security.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {

    private String token;
    private long expiresIn;
    private String username;

}
