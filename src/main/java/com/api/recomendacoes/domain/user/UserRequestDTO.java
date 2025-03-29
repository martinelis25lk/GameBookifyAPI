package com.api.recomendacoes.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import org.springframework.web.multipart.MultipartFile;

public record UserRequestDTO(
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,
        @NotBlank(message = "Password is required") String password,
        String profilePicture,  // Will save as base64 string
        String profileDescription
) {
}
