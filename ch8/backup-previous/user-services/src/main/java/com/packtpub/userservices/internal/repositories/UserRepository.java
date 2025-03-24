package com.packtpub.userservices.internal.repositories;

import com.packtpub.userservices.internal.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    List<User> findAll();
}

