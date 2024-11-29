package com.packtpub.userservices.adapter.datasources.user;

import com.packtpub.userservices.internal.entity.Role;
import com.packtpub.userservices.internal.entity.User;
import com.packtpub.userservices.internal.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserJpaDatasource implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserJpaDatasource(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(this::toDomain);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPhoneNumber(),
                userEntity.getCity(),
                userEntity.getState(),
                userEntity.getCountry(),
                userEntity.getRoles() != null ? userEntity.getRoles().stream().map(this::toRoleDomain).collect(Collectors.toSet()) : null
        );
    }

    private Role toRoleDomain(RoleEntity roleEntity){
        return new Role(roleEntity.getId(), roleEntity.getName());
    }

}
