package com.packtpub.authenticationservices.internal.repositories;

import com.packtpub.authenticationservices.internal.entities.Authentication;

import java.util.Optional;

public interface AuthenticationManagerRepository {
    Optional<Authentication> authenticate(String username, String password);
}
