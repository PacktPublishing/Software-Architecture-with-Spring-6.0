package com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response;

import lombok.Getter;

@Getter
public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

}