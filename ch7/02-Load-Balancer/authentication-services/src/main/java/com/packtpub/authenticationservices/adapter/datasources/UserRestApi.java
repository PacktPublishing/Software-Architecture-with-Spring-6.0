package com.packtpub.authenticationservices.adapter.datasources;

import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
import com.packtpub.authenticationservices.internal.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

@Service
public class UserRestApi implements UserRepository {

    private final RestClient.Builder restClient;

    public UserRestApi(RestClient.Builder restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<String> getRolesByUsername(String username) {

        RoleResponse result = restClient.build()
                .get()
                .uri(URI.create("http://USER-SERVICES/v1/users/" + username + "/roles"))
                .retrieve()
                .body(RoleResponse.class);
        return result.getRoles();
    }

}
