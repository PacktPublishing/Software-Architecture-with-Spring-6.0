package com.packtpub.authenticationservices.adapter.datasources;

import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
import com.packtpub.authenticationservices.config.correlation.CorrelationIdUtil;
import com.packtpub.authenticationservices.internal.repositories.UserRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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

    // Uncomment when testing only circuit breaker
//     @CircuitBreaker(name = "userServices")

    // Uncomment when testing circuit breaker and fallback
     @CircuitBreaker(name = "userServices", fallbackMethod = "getRolesFromCache")

    // Uncomment when testing retry
    //@Retry(name = "userServicesRetry", fallbackMethod = "getRolesFromCache")

    // Uncomment when testing rate limiter
    // @RateLimiter(name = "userServicesRateLimiter")

    // Uncomment for testing Bulkhead
    // @Bulkhead(name = "userServicesBulkhead", type = Bulkhead.Type.SEMAPHORE)
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

    public List<String> getRolesFromCacheRetry(Exception e) {
        System.out.println("Fallback response due to: " + e.getMessage());
        return List.of("ROLE_GUEST");
    }

}
