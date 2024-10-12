package com.packtpub.userservices.adapter.datasources.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    public boolean validateToken(String token) {

        ServiceInstance serviceInstance = discoveryClient.getInstances("AUTHENTICATION-SERVICES").get(0);

        Boolean result = restClient.get()
                .uri(serviceInstance.getUri() + "/v1/api/auth/validate?token={token}", token)
                .retrieve()
                .body(Boolean.class);
        return Boolean.TRUE.equals(result);
    }

}
