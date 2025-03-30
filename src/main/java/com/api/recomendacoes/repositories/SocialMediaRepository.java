package com.api.recomendacoes.repositories;

import com.api.recomendacoes.domain.socialmedia.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialMediaRepository extends JpaRepository<SocialMedia, Integer> {
    Optional<SocialMedia> findByName(String name);
}
