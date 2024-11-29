package com.packtpub.authenticationservices.internal.repositories;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface UserRepository {
    Flux<String> getRolesByUsername(String username);
}
