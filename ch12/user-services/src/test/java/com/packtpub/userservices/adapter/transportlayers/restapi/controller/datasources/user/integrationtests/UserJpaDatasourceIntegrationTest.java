package com.packtpub.userservices.adapter.transportlayers.restapi.controller.datasources.user.integrationtests;

import com.packtpub.userservices.adapter.datasources.user.UserJpaDatasource;
import com.packtpub.userservices.internal.entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Sql({"/init.sql"})
public class UserJpaDatasourceIntegrationTest {

    @Autowired
    private UserJpaDatasource userJpaDatasource;

    final static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    void findByUsername_shouldReturnUser_whenUserExists() {
        String username = "admin@wxauction.com";
        Optional<User> result = userJpaDatasource.findByUsername(username);
        assertEquals(username, result.get().getEmail());
    }

}
