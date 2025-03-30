package com.api.recomendacoes.domain.usersocialmedia;

public record UserSocialMediaResponseDTO(
        Integer id,
        String socialMediaName,
        String socialMediaIconUrl,
        String socialMediaUrl
) {
}
