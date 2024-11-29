package com.packtpub.authenticationservices.internal.usecases;

import com.packtpub.authenticationservices.internal.repositories.TokenRepository;

public class ValidateTokenUseCase {

    private final TokenRepository tokenRepository;

    public ValidateTokenUseCase(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public boolean execute(String token) {
        return tokenRepository.validate(token);
    }
}