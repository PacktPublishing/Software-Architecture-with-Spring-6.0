package com.packtpub.userservices.adapter.datasources.user;

import com.packtpub.userservices.internal.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserJpaDatasourceTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @InjectMocks
    private UserJpaDatasource userJpaDatasource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByUsername_shouldReturnUser_whenUserExists() {
        // Arrange
        String name = "testUser";
        UserEntity userEntity = new UserEntity();
        userEntity.setName("testUser");

        // Simulate the behavior of toDomain (assuming it returns the same User instance)
        when(userJpaRepository.findByUsername(name)).thenReturn(Optional.of(userEntity));

        // Act
        Optional<User> result = userJpaDatasource.findByUsername(name);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(name, result.get().getName());

    }

    @Test
    void findByUsername_shouldReturnEmpty_whenUserDoesNotExist() {
        // Arrange
        when(userJpaRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        // Act
        Optional<UserEntity> result = userJpaRepository.findByUsername("nonExistentUser");

        // Assert
        assertTrue(result.isEmpty());
    }
}