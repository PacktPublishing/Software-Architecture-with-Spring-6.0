package com.packtpub.productservices.adapter.datasources.authentication;

import com.packtpub.productservices.internal.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthenticationRestApi {

    private final RestClient restClient;

    // Use Kubernetes DNS for service discovery
    @Value("${services.authentication.url}")
    private String authenticationServiceUrl;

    public AuthenticationRestApi(RestClient restClient) {
        this.restClient = restClient;
    }

    public AuthenticationUser validateToken(String token) {
        AuthenticationUser authenticationUser = restClient.get()
                .uri(authenticationServiceUrl + "/v1/api/auth/validate?token={token}", token)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new BusinessException(response.getStatusCode().toString(), response.getStatusText());
                })
                .body(AuthenticationUser.class);
        return authenticationUser;
    }
}
