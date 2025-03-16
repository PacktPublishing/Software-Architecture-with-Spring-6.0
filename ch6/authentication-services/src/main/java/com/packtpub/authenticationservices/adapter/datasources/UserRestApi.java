package com.packtpub.authenticationservices.adapter.datasources;

import com.packtpub.authenticationservices.internal.repositories.UserRepository;
import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class UserRestApi implements UserRepository {

    private final RestClient restClient;

    @Value("${user.service.url}")
    private String userServiceUrl;

    public UserRestApi(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<String> getRolesByUsername(String username) {
        RoleResponse result = restClient.get()
                .uri(userServiceUrl + "/v1/users/{username}/roles", username)
                .retrieve()
                .body(RoleResponse.class);
        return result.getRoles();
    }

}
