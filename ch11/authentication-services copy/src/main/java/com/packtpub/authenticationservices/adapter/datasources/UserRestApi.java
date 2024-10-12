package com.packtpub.authenticationservices.adapter.datasources;

import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
import com.packtpub.authenticationservices.config.correlation.CorrelationIdUtil;
import com.packtpub.authenticationservices.internal.repositories.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
public class UserRestApi implements UserRepository {

    private final RestClient.Builder restClient;

    public UserRestApi(RestClient.Builder restClient) {
        this.restClient = restClient;
    }

//     @CircuitBreaker(name = "userServices", fallbackMethod = "getRolesFromCache")
    @Override
    public List<String> getRolesByUsername(String username) {
        RoleResponse result = restClient.build()
                .get()
                .uri(URI.create("http://USER-SERVICES/v1/users/" + username + "/roles"))
                .header("x-correlation-id", CorrelationIdUtil.getCorrelationId())
                .retrieve()
                .body(RoleResponse.class);
        return result.getRoles();
    }

    public List<String> getRolesFromCache(String username, Throwable throwable) {
        System.out.println("Fallback response due to: " + throwable.getMessage());
        return List.of("ROLE_GUEST");
    }

}
