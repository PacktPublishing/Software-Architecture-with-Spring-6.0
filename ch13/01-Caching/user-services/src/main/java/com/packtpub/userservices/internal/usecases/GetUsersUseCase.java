package com.packtpub.userservices.internal.usecases;

import com.packtpub.userservices.internal.entity.User;
import com.packtpub.userservices.internal.repositories.UserRepository;

import java.util.List;

public class GetUsersUseCase {
    private final UserRepository userRepository;

    public GetUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> execute() {
        return userRepository.findAll();
    }

}