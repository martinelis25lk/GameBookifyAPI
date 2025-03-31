package com.api.recomendacoes.repositories;

import com.api.recomendacoes.domain.user.User;
import com.api.recomendacoes.domain.usersocialmedia.UserSocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSocialMediaRepository extends JpaRepository<UserSocialMedia, Integer> {
    List<UserSocialMedia> findByUserId(User user);
}
