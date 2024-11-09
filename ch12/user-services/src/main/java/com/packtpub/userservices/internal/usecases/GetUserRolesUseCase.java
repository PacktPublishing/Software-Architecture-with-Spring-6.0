package com.packtpub.userservices.internal.usecases;

import com.packtpub.userservices.internal.entity.User;
import com.packtpub.userservices.internal.repositories.UserRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetUserRolesUseCase {
    private final UserRepository userRepository;

    public GetUserRolesUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> execute(String username) {
        List<String> roles = new ArrayList<>();
        Optional<User> user = userRepository.findByUsername(username);
        user.ifPresent(value -> value.getRoles().forEach(role -> roles.add(role.getName())));
        return roles;
    }

}