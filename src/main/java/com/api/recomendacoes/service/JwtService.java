package com.api.recomendacoes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    @Autowired
    private final JwtEncoder jwtEncoder;

    public Map<String, Object> generateToken(Authentication authentication) {
        // Generate a JWT token using the authentication information

        // Set the token expiration time
        Instant expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));

        log.info("Expires at: {}", expiresAt);

        // Controls the users permissions. Exemple: "ROLE_USER,ROLE_ADMIN"
        String scopes = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        var claims = JwtClaimsSet.builder().issuer("api-recomendacoes")
                .expiresAt(expiresAt)
                .subject(authentication.getName())
                .claim("scope", scopes)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        // Encode the JWT token using the JwtEncoder
        return Map.of("token", token, "expiresAt", expiresAt.toString());
    }
}
