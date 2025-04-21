package com.packtpub.userservices.adapter.transportlayers.restapi.controller;

import com.packtpub.userservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
import com.packtpub.userservices.adapter.transportlayers.restapi.dto.response.UserResponse;
import com.packtpub.userservices.internal.entity.User;
import com.packtpub.userservices.internal.usecases.GetUserRolesUseCase;
import com.packtpub.userservices.internal.usecases.GetUsersUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final GetUserRolesUseCase getUserRolesUseCase;
    private final GetUsersUseCase getUsersUseCase;

    @Operation(summary = "Get all users", description = "Returns a list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<User> users = getUsersUseCase.execute();
        List<UserResponse> userResponses = users.stream().map(n -> new UserResponse(n.getId(), n.getName(), n.getEmail(), n.getPhoneNumber(), n.getCity(), n.getState(), n.getCountry(), n.getRoles())).collect(Collectors.toList());
        return !users.isEmpty() ? new ResponseEntity<>(userResponses, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{username}/roles")
    public ResponseEntity<RoleResponse> getUserRoles(@PathVariable("username") String username, @RequestHeader("traceparent") String traceparent) throws TimeoutException, InterruptedException {

        Thread current = Thread.currentThread();
        log.info("Current thread: {} (isVirtual: {})", current.getName(), current.isVirtual());

        log.info("Received traceparent: {}", traceparent);  // Verify trace propagation

        List<String> roles = getUserRolesUseCase.execute(username);
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setRoles(roles);
        return !roles.isEmpty() ? new ResponseEntity<>(roleResponse, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}