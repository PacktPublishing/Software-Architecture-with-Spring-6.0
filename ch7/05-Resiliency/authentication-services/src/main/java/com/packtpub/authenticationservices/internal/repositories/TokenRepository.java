package com.packtpub.authenticationservices.internal.repositories;

import com.packtpub.authenticationservices.internal.entities.Authentication;

public interface TokenRepository {
    String generate(Authentication user);
    Authentication validate(String token);
}
