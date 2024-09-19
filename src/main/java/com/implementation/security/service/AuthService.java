package com.implementation.security.service;

import com.implementation.security.config.TokenProvider;
import com.implementation.security.config.TokenResponse;
import com.implementation.security.dto.LoginRequest;
import com.implementation.security.dto.NewAccountRequest;
import com.implementation.security.entity.Users;
import com.implementation.security.repository.IUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUsersRepository usersRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public void createAccount(NewAccountRequest request) {
        usersRepository.save(Users.builder()
                .identifier(UUID.randomUUID().toString())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singletonList(roleService.getRoleByName(request.getRoleName())))
                .build());
    }

    public TokenResponse login(LoginRequest request) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
            return tokenProvider.generateToken(authentication);
        } catch (AuthenticationException e) {
            throw new Exception("Credenciais inválidas: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Erro ao autenticar: " + e.getMessage());
        }
    }
}
