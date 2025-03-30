package com.api.recomendacoes.domain.user;

public record SocialMediasUserDTO(
        Integer id,
        String socialMediaName,
        String socialMediaIconUrl,
        String socialMediaUrl
) {
}
