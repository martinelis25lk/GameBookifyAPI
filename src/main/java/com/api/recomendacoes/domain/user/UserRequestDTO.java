package com.api.recomendacoes.domain.user;

import org.springframework.web.multipart.MultipartFile;

public record UserRequestDTO(String username, String email, String password, MultipartFile profilePicture, String profileDescription) {
}
