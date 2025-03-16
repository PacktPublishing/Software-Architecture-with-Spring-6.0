package com.packtpub.authorizationserver.internal.repositories;

import com.packtpub.authorizationserver.internal.entities.Authentication;

import java.util.Optional;

public interface AuthenticationManagerRepository {
    Optional<Authentication> authenticate(String username, String password);
}
