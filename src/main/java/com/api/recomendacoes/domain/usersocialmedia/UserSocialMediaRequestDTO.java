package com.api.recomendacoes.domain.usersocialmedia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserSocialMediaRequestDTO(
        @NotNull(message = "Social media ID is required") Integer socialMediaId,
        @NotBlank (message = "Social media url is required") String socialMediaUrl
) {
}
