package com.api.recomendacoes.service;

import com.api.recomendacoes.domain.socialmedia.SocialMedia;
import com.api.recomendacoes.domain.socialmedia.SocialMediaRequestDTO;
import com.api.recomendacoes.errors.socialmedia.SocialMediaAlrealdyExistsException;
import com.api.recomendacoes.repositories.SocialMediaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocialMediaService {

    private final SocialMediaRepository socialMediaRepository;

    @Transactional
    public SocialMedia saveSocialMedia(SocialMediaRequestDTO socialMediaRequestDTO) {
        if (socialMediaRepository.findByName(socialMediaRequestDTO.name()).isPresent()) {
            log.warn("Social media with name {} already exists", socialMediaRequestDTO.name());
            throw new SocialMediaAlrealdyExistsException("Social media with name " + socialMediaRequestDTO.name() + " already exists");
        }
        // Save the new social media to the database
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setName(socialMediaRequestDTO.name());
        socialMedia.setIcon_url(socialMediaRequestDTO.icon_url());
        socialMediaRepository.save(socialMedia);
        log.info("Social media with name {} saved successfully", socialMediaRequestDTO.name());
        return socialMedia;
    }
}
