package com.packtpub.authenticationservices.internal.usecases;

import com.packtpub.authenticationservices.internal.entities.Authentication;
import com.packtpub.authenticationservices.internal.repositories.AuthenticationManagerRepository;
import com.packtpub.authenticationservices.internal.repositories.TokenRepository;
import com.packtpub.authenticationservices.internal.repositories.UserRepository;

import java.util.Optional;
import java.util.concurrent.TimeoutException;

public class GenerateTokenUseCase {

    private final AuthenticationManagerRepository authenticationManagerRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public GenerateTokenUseCase(AuthenticationManagerRepository authenticationManagerRepository, UserRepository userRepository, TokenRepository tokenRepository) {
        this.authenticationManagerRepository = authenticationManagerRepository;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public Optional<String> execute(String username, String password) {
        Optional<Authentication> authentication = authenticationManagerRepository.authenticate(username, password);
        if (authentication.isPresent()) {
            authentication.get().setRoles(userRepository.getRolesByUsername(username));
            return Optional.of(tokenRepository.generate(authentication.get()));
        }
        return Optional.empty();
    }

}