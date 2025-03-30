package com.api.recomendacoes.service;

import com.api.recomendacoes.domain.socialmedia.SocialMedia;
import com.api.recomendacoes.domain.user.User;
import com.api.recomendacoes.domain.usersocialmedia.UserSocialMedia;
import com.api.recomendacoes.domain.usersocialmedia.UserSocialMediaRequestDTO;
import com.api.recomendacoes.errors.socialmedia.SocialMediaNotFoundException;
import com.api.recomendacoes.errors.users.UserNotFoundException;
import com.api.recomendacoes.repositories.SocialMediaRepository;
import com.api.recomendacoes.repositories.UserRepository;
import com.api.recomendacoes.repositories.UserSocialMediaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserSocialMediaService {

    private final UserSocialMediaRepository userSocialMediaRepository;
    private final UserRepository userRepository;
    private final SocialMediaRepository socialMediaRepository;

    @Transactional
    public UserSocialMedia saveUserSocialMedia(Integer userId, UserSocialMediaRequestDTO userSocialMediaRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        SocialMedia socialMedia = socialMediaRepository.findById(userSocialMediaRequestDTO.socialMediaId())
                .orElseThrow(() -> new SocialMediaNotFoundException("Social media not found with id: " + userSocialMediaRequestDTO.socialMediaId()));

        UserSocialMedia userSocialMedia = new UserSocialMedia();
        userSocialMedia.setUserId(user);
        userSocialMedia.setSocialMediaId(socialMedia);
        userSocialMedia.setSocialMediaUrl(userSocialMediaRequestDTO.socialMediaUrl());
        userSocialMediaRepository.save(userSocialMedia);
        log.info("User social media with id {} saved successfully", userSocialMedia.getId());
        return userSocialMedia;
    }
}
