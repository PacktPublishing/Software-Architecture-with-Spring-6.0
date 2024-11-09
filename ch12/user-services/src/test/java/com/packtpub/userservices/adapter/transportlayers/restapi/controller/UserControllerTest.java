package com.packtpub.userservices.adapter.transportlayers.restapi.controller;

import com.packtpub.userservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
import com.packtpub.userservices.internal.usecases.GetUserRolesUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private GetUserRolesUseCase getUserRolesUseCase;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserRoles_shouldReturnRoles_whenRolesExist() throws TimeoutException, InterruptedException {

        String username = "testUser";
        String traceparent = "00-4bf92f3577b34da6a3ce929d0e0e4736-00";
        List<String> roles = List.of("ROLE_USER", "ROLE_ADMIN");

        when(getUserRolesUseCase.execute(username)).thenReturn(roles);

        ResponseEntity<RoleResponse> response = userController.getUserRoles(username, traceparent);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(roles, response.getBody().getRoles());
    }

    @Test
    void getUserRoles_shouldReturnNotFound_whenRolesDoNotExist() throws TimeoutException, InterruptedException {
        // Arrange
        String username = "testUser";
        String traceparent = "00-4bf92f3577b34da6a3ce929d0e0e4736-00";

        when(getUserRolesUseCase.execute(username)).thenReturn(List.of());

        // Act
        ResponseEntity<RoleResponse> response = userController.getUserRoles(username, traceparent);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}