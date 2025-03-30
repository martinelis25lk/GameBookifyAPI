package com.api.recomendacoes.domain.usersocialmedia;

import com.api.recomendacoes.domain.socialmedia.SocialMedia;
import com.api.recomendacoes.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_social_medias")
@Entity
public class UserSocialMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "social_media_id", nullable = false)
    private SocialMedia socialMediaId;

    @Column(name = "social_media_url", nullable = false)
    private String socialMediaUrl;
}
