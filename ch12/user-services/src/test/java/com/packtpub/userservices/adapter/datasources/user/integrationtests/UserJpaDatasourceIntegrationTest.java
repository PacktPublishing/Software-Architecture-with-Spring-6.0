package com.packtpub.userservices.adapter.datasources.user.integrationtests;

import com.packtpub.userservices.adapter.datasources.user.UserJpaDatasource;
import com.packtpub.userservices.internal.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
@Sql({"/init.sql"})
class UserJpaDatasourceIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    private UserJpaDatasource userJpaDatasource;

    @Test
    void findByUsername_shouldReturnUser_whenUserExists() {
        String username = "admin@wxauction.com";
        Optional<User> result = userJpaDatasource.findByUsername(username);

        assertThat(result).isPresent()
                .get()
                .extracting(User::getEmail)
                .isEqualTo(username);
    }
}
