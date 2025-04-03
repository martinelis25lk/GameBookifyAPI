package com.api.recomendacoes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;

    public Map<String, Object> authenticate(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }

}