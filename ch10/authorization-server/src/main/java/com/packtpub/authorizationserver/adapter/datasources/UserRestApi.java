package com.packtpub.authorizationserver.adapter.datasources;

import com.packtpub.authorizationserver.adapter.transportlayers.restapi.dto.response.RoleResponse;
import com.packtpub.authorizationserver.internal.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserRestApi implements UserRepository {

    private final RestClient.Builder restClient;

    public UserRestApi(RestClient.Builder restClient) {
        this.restClient = restClient;
    }

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Override
    public List<String> getRolesByUsername(String username) {
        RoleResponse result = restClient.build()
                .get()
                .uri(userServiceUrl + "/v1/users/{username}/roles", username)
                .retrieve()
                .body(RoleResponse.class);
        return result != null ? result.getRoles() : Collections.emptyList();
    }
}
