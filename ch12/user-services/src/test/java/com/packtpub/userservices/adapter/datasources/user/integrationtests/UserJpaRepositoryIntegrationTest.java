package com.packtpub.userservices.adapter.datasources.user.integrationtests;

import com.packtpub.userservices.adapter.datasources.user.UserEntity;
import com.packtpub.userservices.adapter.datasources.user.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(value = "/init.sql")
class UserJpaRepositoryIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Test
    void findByUsername_shouldReturnUser_whenEmailExists() {
        String email = "admin@wxauction.com";

        Optional<UserEntity> result = userJpaRepository.findByUsername(email);

        assertThat(result).isPresent()
                .get()
                .extracting(UserEntity::getEmail)
                .isEqualTo(email);
    }

    @Test
    void findByUsername_shouldReturnEmpty_whenEmailDoesNotExist() {
        Optional<UserEntity> result = userJpaRepository.findByUsername("nonexistent@example.com");

        assertThat(result).isNotPresent();
    }
}