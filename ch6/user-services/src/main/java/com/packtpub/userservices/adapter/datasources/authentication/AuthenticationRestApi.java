package com.packtpub.userservices.adapter.datasources.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
@Service
public class AuthenticationRestApi {

    private final RestClient restClient;

    @Value("${authentication.service.url}")
    private String authenticationServiceUrl;

    public AuthenticationRestApi(RestClient restClient) {
        this.restClient = restClient;
    }

    public boolean validateToken(String token) {
        Boolean result = restClient.get()
                .uri(authenticationServiceUrl + "/v1/api/auth/validate?token={token}", token)
                .retrieve()
                .body(Boolean.class);
        return Boolean.TRUE.equals(result);
    }

}
