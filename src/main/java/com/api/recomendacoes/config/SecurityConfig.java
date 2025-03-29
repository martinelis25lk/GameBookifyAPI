package com.api.recomendacoes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


// I put this because I'm using the spring security dependency to hash the password,
// but I don't want to use the security features of spring security. It's forcing me to authenticate.
// This class is to disable the security features of spring security.
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Allow all requests
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF (optional)
                .formLogin(AbstractHttpConfigurer::disable) // Disable login form
                .httpBasic(AbstractHttpConfigurer::disable); // Disable basic auth

        return http.build();
    }
}
