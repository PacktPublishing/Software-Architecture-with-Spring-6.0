//package com.packtpub.authenticationservices.adapter.datasources;
//
//import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.web.client.RestClient;
//
//import java.net.URI;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class UserRestApiTest {
//
//    @Mock
//    private RestClient.Builder restClientBuilder;
//
//    @Mock
//    private RestClient restClient;
//
//    @Mock
//    private RestClient requestSpecification;
//
//    @InjectMocks
//    private UserRestApi userRestApi;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getRolesByUsername_shouldReturnRoles_whenUserExists() {
//        // Arrange
//        String username = "testUser";
//        List<String> expectedRoles = List.of("ROLE_USER", "ROLE_ADMIN");
//
//        RoleResponse roleResponse = new RoleResponse();
//        roleResponse.setRoles(expectedRoles);
//
//        when(restClientBuilder.build()).thenReturn(restClient);
//        when(restClient.get()).thenReturn(List.of("ROLE_USER", "ROLE_ADMIN"));
//
//        // Act
//        List<String> roles = userRestApi.getRolesByUsername(username);
//
//        // Assert
//        assertEquals(expectedRoles, roles);
//    }
//
//    @Test
//    void getRolesFromCache_shouldReturnGuestRole_whenExceptionOccurs() {
//        // Arrange
//        String username = "testUser";
//        Throwable exception = new RuntimeException("Service unavailable");
//
//        // Act
//        List<String> roles = userRestApi.getRolesFromCache(username, exception);
//
//        // Assert
//        assertEquals(List.of("ROLE_GUEST"), roles);
//    }
//}