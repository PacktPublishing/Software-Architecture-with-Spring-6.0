package com.packtpub.authenticationservices.internal.usecases;

import com.packtpub.authenticationservices.internal.entities.Authentication;
import com.packtpub.authenticationservices.internal.repositories.TokenRepository;

public class ValidateTokenUseCase {

    private final TokenRepository tokenRepository;

    public ValidateTokenUseCase(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Authentication execute(String token) {
        return tokenRepository.validate(token);
    }
}