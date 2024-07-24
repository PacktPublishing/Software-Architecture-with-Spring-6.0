package com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.request;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
