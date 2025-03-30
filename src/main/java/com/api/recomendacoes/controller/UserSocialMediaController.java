package com.api.recomendacoes.controller;

import com.api.recomendacoes.domain.usersocialmedia.UserSocialMedia;
import com.api.recomendacoes.domain.usersocialmedia.UserSocialMediaRequestDTO;
import com.api.recomendacoes.domain.usersocialmedia.UserSocialMediaResponseDTO;
import com.api.recomendacoes.service.UserSocialMediaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/social-media")
public class UserSocialMediaController {

    @Autowired
    private UserSocialMediaService userSocialMediaService;

    @PostMapping
    public ResponseEntity<UserSocialMediaResponseDTO> createUserSocialMedia(@PathVariable Integer userId, @Valid @RequestBody UserSocialMediaRequestDTO userSocialMediaRequestDTO) {
        UserSocialMedia userSocialMedia = userSocialMediaService.saveUserSocialMedia(userId, userSocialMediaRequestDTO);
        
        UserSocialMediaResponseDTO userSocialMediaResponseDTO = new UserSocialMediaResponseDTO(
                userSocialMedia.getId(),
                userSocialMedia.getSocialMediaId().getName(),
                userSocialMedia.getSocialMediaId().getIcon_url(),
                userSocialMedia.getSocialMediaUrl()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(userSocialMediaResponseDTO);
    }
}
