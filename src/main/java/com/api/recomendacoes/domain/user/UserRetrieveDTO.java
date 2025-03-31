package com.api.recomendacoes.domain.user;

import java.util.List;

public record UserRetrieveDTO(
        Integer id,
        String username,
        String email,
        String profilePicture,
        String profileDescription,
        List<SocialMediasUserDTO> socialMediasUser
) {
}
