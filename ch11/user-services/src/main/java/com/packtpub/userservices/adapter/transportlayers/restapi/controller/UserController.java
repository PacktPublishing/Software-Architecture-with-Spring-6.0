package com.packtpub.userservices.adapter.transportlayers.restapi.controller;

import com.packtpub.userservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
import com.packtpub.userservices.internal.usecases.GetUserRolesUseCase;
import com.packtpub.userservices.internal.usecases.GetUsersUseCase;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapGetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final GetUserRolesUseCase getUserRolesUseCase;
    private final GetUsersUseCase getUsersUseCase;

    @GetMapping("/{username}/roles")
    public ResponseEntity<RoleResponse> getRoles(@PathVariable String username, @RequestHeader HttpHeaders headers) {
        // Extract trace context from incoming headers
        Context extractedContext = GlobalOpenTelemetry.getPropagators()
                .getTextMapPropagator()
                .extract(Context.current(), headers, HttpHeadersGetter.INSTANCE);

        // Set the extracted context as the current context
        try (var scope = extractedContext.makeCurrent()) {
            // Handle the request
           // return new RoleResponse(List.of("ROLE_USER", "ROLE_ADMIN"));
            List<String> roles = getUserRolesUseCase.execute(username);
            RoleResponse roleResponse = new RoleResponse();
            roleResponse.setRoles(roles);
            return !roles.isEmpty() ? new ResponseEntity<>(roleResponse, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    // TextMapGetter to extract trace headers from the request
    private static final class HttpHeadersGetter implements TextMapGetter<HttpHeaders> {
        static final HttpHeadersGetter INSTANCE = new HttpHeadersGetter();

        @Override
        public Iterable<String> keys(HttpHeaders carrier) {
            return carrier.keySet();
        }

        @Override
        public String get(HttpHeaders carrier, String key) {
            return carrier.getFirst(key);
        }
    }
}
//public class UserController {
//
//    private final GetUserRolesUseCase getUserRolesUseCase;
//    private final GetUsersUseCase getUsersUseCase;
//
//    public UserController(GetUserRolesUseCase getUserRolesUseCase, GetUsersUseCase getUsersUseCase) {
//        this.getUserRolesUseCase = getUserRolesUseCase;
//        this.getUsersUseCase = getUsersUseCase;
//    }
//
//    @Operation(summary = "Get all users", description = "Returns a list of all users")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successful operation",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = List.class))),
//            @ApiResponse(responseCode = "400", description = "Invalid request"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
//    @GetMapping
//    //@PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<List<UserResponse>> getUsers() {
//        List<User> users = getUsersUseCase.execute();
//        List<UserResponse> userResponses = users.stream().map(n -> new UserResponse(n.getId(), n.getName(), n.getEmail(), n.getPhoneNumber(), n.getCity(), n.getState(), n.getCountry(), n.getRoles())).collect(Collectors.toList());
//        return !users.isEmpty() ? new ResponseEntity<>(userResponses, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping(value = "/{username}/roles")
//    public ResponseEntity<RoleResponse> getUserRoles(@PathVariable("username") String username, @RequestHeader("traceparent") String traceparent) throws TimeoutException, InterruptedException {
//        log.info("Received traceparent: {}", traceparent);  // Verify trace propagation
//
//        // Enable to test bulkhead
//        // Thread.sleep(20000);
//
//        List<String> roles = getUserRolesUseCase.execute(username);
//        RoleResponse roleResponse = new RoleResponse();
//        roleResponse.setRoles(roles);
//        return !roles.isEmpty() ? new ResponseEntity<>(roleResponse, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//}