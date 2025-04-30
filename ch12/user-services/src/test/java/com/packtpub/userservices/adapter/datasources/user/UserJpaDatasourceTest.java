package com.packtpub.userservices.adapter.datasources.user;

import com.packtpub.userservices.internal.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class UserJpaDatasourceTest {

    @Test
    void givenUserExists_whenFindByUsername_thenReturnUser() {

        UserJpaRepository userJpaRepository = mock();
        UserJpaDatasource userJpaDatasource = new UserJpaDatasource(userJpaRepository);

        String name = "testUser";
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);

        when(userJpaRepository.findByUsername(name)).thenReturn(Optional.of(userEntity));

        Optional<User> result = userJpaDatasource.findByUsername(name);

        assertThat(result).hasValueSatisfying(it -> assertThat(it.getName()).isEqualTo(name));

    }


    @Test
    void findByUsername_shouldReturnEmpty_whenUserDoesNotExist() {

        UserJpaRepository userJpaRepository = mock();
        UserJpaDatasource userJpaDatasource = new UserJpaDatasource(userJpaRepository);

        when(userJpaRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        Optional<User> result = userJpaDatasource.findByUsername("nonExistentUser");

        assertThat(result).isNotPresent();
    }
}