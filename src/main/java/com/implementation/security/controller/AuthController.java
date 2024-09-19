package com.implementation.security.controller;

import com.implementation.security.config.TokenResponse;
import com.implementation.security.dto.LoginRequest;
import com.implementation.security.dto.NewAccountRequest;
import com.implementation.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<String> createAccount(@RequestBody NewAccountRequest request){
        service.createAccount(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) throws Exception{
        return new ResponseEntity<>(service.login(request), HttpStatus.CREATED);
    }
}
