package com.packtpub.userservices.adapter.transportlayers.restapi.controller.integrationtests;

import com.packtpub.userservices.UserServicesApplication;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(
        classes = UserServicesApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Testcontainers
@Sql("/init.sql")
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine");

    @Test
    void getUserRoles_shouldReturnRoles_whenUserExists() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        given()
                .accept(ContentType.JSON)
                .header("traceparent", "00-abcdef1234567890abcdef1234567890-abcdef1234567890-01")
                .when()
                .get("/v1/users/admin@wxauction.com/roles")
                .then()
                .log().all()
                .statusCode(200)
                .body("roles", not(empty()))
                .body("roles", hasItem("ROLE_USER"));
    }
}