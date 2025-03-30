package com.api.recomendacoes.errors.socialmedia;

public class SocialMediaNotFoundException extends RuntimeException {
    public SocialMediaNotFoundException(String message) {
        super(message);
    }

    public SocialMediaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
