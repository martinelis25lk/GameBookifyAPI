package com.api.recomendacoes.errors.socialmedia;

public class SocialMediaAlrealdyExistsException extends RuntimeException {
    public SocialMediaAlrealdyExistsException(String message) {
        super(message);
    }

    public SocialMediaAlrealdyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
