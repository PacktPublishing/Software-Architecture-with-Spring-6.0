package com.packtpub.userservices.internal.usecases.integrationtests;

import com.packtpub.userservices.adapter.datasources.user.UserJpaDatasource;
import com.packtpub.userservices.internal.usecases.GetUserRolesUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@Sql({"/init.sql"})
public class GetUserRolesUseCaseIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    private UserJpaDatasource userJpaDatasource;

    private GetUserRolesUseCase getUserRolesUseCase;

    @BeforeEach
    void setup() {
        getUserRolesUseCase = new GetUserRolesUseCase(userJpaDatasource);
    }

    @Test
    void givenUserExists_whenExecute_thenReturnsRoles() {
        String username = "admin@wxauction.com";
        List<String> roles = getUserRolesUseCase.execute(username);
        assertThat(roles).contains("ROLE_ADMIN");
    }

    @Test
    void givenNoUser_whenExecute_thenReturnsAnonymousRole() {
        String username = "ghost@wxauction.com";
        List<String> roles = getUserRolesUseCase.execute(username);
        assertThat(roles).containsExactly("ANONYMOUS");
    }
}