package com.packtpub.bidservices.adapter.datasources.authentication;

import com.packtpub.bidservices.internal.exception.BusinessException;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
public class AuthenticationRestApi {

    private final RestClient restClient;

    private final DiscoveryClient discoveryClient;

    public AuthenticationRestApi(RestClient restClient, DiscoveryClient discoveryClient) {
        this.restClient = restClient;
        this.discoveryClient = discoveryClient;
    }

    public AuthenticationUser validateToken(String token) {

        ServiceInstance serviceInstance = discoveryClient.getInstances("AUTHENTICATION-SERVICES").getFirst();

        return restClient.get()
                .uri(serviceInstance.getUri() + "/v1/api/auth/validate?token={token}", token)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new BusinessException(response.getStatusCode().toString(), response.getStatusText());
                })
                .body(AuthenticationUser.class);
    }

}