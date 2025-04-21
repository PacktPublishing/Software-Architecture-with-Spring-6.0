package com.packtpub.authenticationservices.adapter.datasources;

import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
import com.packtpub.authenticationservices.config.correlation.CorrelationIdUtil;
import com.packtpub.authenticationservices.internal.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class UserRestApi implements UserRepository {

    private final WebClient webClient;

    public UserRestApi(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Flux<String> getRolesByUsername(String username) {
        return webClient.get()
                .uri("http://USER-SERVICES/v1/users/{username}/roles", username)
                .header("traceparent", "traceparent_text")
                .retrieve()
                .onStatus(status -> status != HttpStatus.OK,
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("Error: " + errorBody))))
                .bodyToFlux(String.class);
    }

}
