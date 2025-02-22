package com.packtpub.authenticationservices.adapter.transportlayers.restapi.controller;

import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.request.AuthenticationRequest;
import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response.AuthenticationResponse;
import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response.AuthenticationUserResponse;
import com.packtpub.authenticationservices.internal.entities.Authentication;
import com.packtpub.authenticationservices.internal.usecases.GenerateTokenUseCase;
import com.packtpub.authenticationservices.internal.usecases.ValidateTokenUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/v1/api/auth")
@RestController
public class AuthenticationController {

    private final GenerateTokenUseCase generateTokenUseCase;
    private final ValidateTokenUseCase validateTokenUseCase;

    public AuthenticationController(GenerateTokenUseCase generateTokenUseCase, ValidateTokenUseCase validateTokenUseCase) {
        this.generateTokenUseCase = generateTokenUseCase;
        this.validateTokenUseCase = validateTokenUseCase;
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        final Optional<String> token = generateTokenUseCase.execute(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return ResponseEntity.ok(new AuthenticationResponse(token.get()));
    }

    @GetMapping("/validate")
    public ResponseEntity<AuthenticationUserResponse> validateToken(@RequestParam String token) {
        final Authentication authentication = validateTokenUseCase.execute(token);
        return authentication !=  null ? ResponseEntity.ok(new AuthenticationUserResponse(authentication.getUsername(), authentication.getRoles())) : null;
    }
}
