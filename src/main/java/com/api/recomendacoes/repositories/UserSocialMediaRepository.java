package com.api.recomendacoes.repositories;

import com.api.recomendacoes.domain.usersocialmedia.UserSocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSocialMediaRepository extends JpaRepository<UserSocialMedia, Integer> {
}
