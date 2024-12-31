package com.packtpub.authenticationservices.adapter.datasources;

import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
import com.packtpub.authenticationservices.internal.exceptions.BusinessException;
import com.packtpub.authenticationservices.internal.repositories.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@Slf4j
public class UserRestApi implements UserRepository {

    @Value("${user-services.url}")
    private String userServiceUrl;

    private final RestClient restClient;

    public UserRestApi(RestClient restClient) {
        this.restClient = restClient;
    }

     @CircuitBreaker(name = "userServices", fallbackMethod = "getRolesFromCache")
     public List<String> getRolesByUsername(String username) {
         log.info("{}/v1/users/{}/roles", userServiceUrl, username);
         RoleResponse result = restClient.get()
                 .uri(userServiceUrl + "/v1/users/" + username + "/roles")
                 .retrieve()
                 .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                     throw new BusinessException(response.getStatusCode().toString(), response.getStatusText());
                 })
                 .body(RoleResponse.class);
         return result.getRoles();
     }

    public List<String> getRolesFromCache(String username, Throwable throwable) {
        log.info("Fallback response due to: {}", throwable.getMessage());
        return List.of("ROLE_GUEST");
    }

    public List<String> getRolesFromCacheRetry(Exception e) {
        log.info("Fallback response due to: {}", e.getMessage());
        return List.of("ROLE_GUEST");
    }

}
