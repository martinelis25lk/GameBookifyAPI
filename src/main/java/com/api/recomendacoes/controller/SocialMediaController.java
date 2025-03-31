package com.api.recomendacoes.controller;

import com.api.recomendacoes.domain.socialmedia.SocialMedia;
import com.api.recomendacoes.domain.socialmedia.SocialMediaRequestDTO;
import com.api.recomendacoes.domain.socialmedia.SocialMediaResponseDTO;
import com.api.recomendacoes.service.SocialMediaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<List<SocialMediaResponseDTO>> getSocialMedia(@RequestParam(required = false) String name) {
        // If a name is provided, find social media by name
        // Otherwise, find all social media
        List<SocialMedia> socialMedias = socialMediaService.findSocialMedias(Optional.ofNullable(name));

        List<SocialMediaResponseDTO> socialMediaResponseDTOs = socialMedias.stream()
                .map(socialMedia -> new SocialMediaResponseDTO(
                        socialMedia.getId(),
                        socialMedia.getName(),
                        socialMedia.getIcon_url()
                ))
                .toList();

        return ResponseEntity.ok(socialMediaResponseDTOs);
    }
}
