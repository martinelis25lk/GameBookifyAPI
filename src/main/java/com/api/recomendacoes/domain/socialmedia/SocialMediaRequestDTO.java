package com.api.recomendacoes.domain.socialmedia;

import jakarta.validation.constraints.NotBlank;

public record SocialMediaRequestDTO(
        @NotBlank (message = "Social media name is required") String name,
        @NotBlank (message = "Social media icon URL is required") String icon_url
) {
}
