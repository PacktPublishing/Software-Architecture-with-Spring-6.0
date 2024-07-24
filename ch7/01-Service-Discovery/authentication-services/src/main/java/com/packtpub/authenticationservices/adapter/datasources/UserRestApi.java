package com.packtpub.authenticationservices.adapter.datasources;

import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
import com.packtpub.authenticationservices.internal.repositories.UserRepository;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class UserRestApi implements UserRepository {

    private final RestClient restClient;
    private final DiscoveryClient discoveryClient;

    public UserRestApi(RestClient restClient, DiscoveryClient discoveryClient) {
        this.restClient = restClient;
        this.discoveryClient = discoveryClient;
    }

    @Override
    public List<String> getRolesByUsername(String username) {
        ServiceInstance serviceInstance = discoveryClient.getInstances("USER-SERVICES").get(0);
        RoleResponse result = restClient.get()
                .uri(serviceInstance.getUri() + "/v1/users/{username}/roles", username)
                .retrieve()
                .body(RoleResponse.class);
        return result.getRoles();
    }

}
