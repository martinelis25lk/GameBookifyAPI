package com.api.recomendacoes.controller;

import com.api.recomendacoes.domain.socialmedia.SocialMedia;
import com.api.recomendacoes.domain.socialmedia.SocialMediaRequestDTO;
import com.api.recomendacoes.service.SocialMediaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social-media")
public class SocialMediaController {

    @Autowired
    private SocialMediaService socialMediaService;

    @PostMapping
    public ResponseEntity<SocialMedia> createSocialMedia(@Valid @RequestBody SocialMediaRequestDTO socialMediaRequestDTO) {
        SocialMedia socialMedia = socialMediaService.saveSocialMedia(socialMediaRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(socialMedia);
    }
}
