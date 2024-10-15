package com.packtpub.authenticationservices.adapter.datasources;

import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
import com.packtpub.authenticationservices.config.correlation.CorrelationIdUtil;
import com.packtpub.authenticationservices.internal.repositories.UserRepository;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapSetter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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

    @Override
    public List<String> getRolesByUsername(String username) {
        // Build the HTTP request with trace headers
        RoleResponse result = restClient.build()
                .get()
                .uri(URI.create("http://USER-SERVICES/v1/users/" + username + "/roles"))
                .headers(httpHeaders -> {
                    // Propagate trace context
                    Context currentContext = Context.current();
                    GlobalOpenTelemetry.getPropagators().getTextMapPropagator()
                            .inject(currentContext, httpHeaders, HttpHeadersSetter.INSTANCE);

                    // Custom correlation ID (optional)
                    httpHeaders.add("x-correlation-id", CorrelationIdUtil.getCorrelationId());
                })
                .retrieve()
                .body(RoleResponse.class);
        log.info("Outgoing traceparent: {}", Span.current().getSpanContext().getTraceId());
        return result.getRoles();
    }

    public List<String> getRolesFromCache(String username, Throwable throwable) {
        log.warn("Fallback response due to: {}", throwable.getMessage());
        return List.of("ROLE_GUEST");
    }

    // TextMapSetter for injecting headers
    private static final class HttpHeadersSetter implements TextMapSetter<HttpHeaders> {
        static final HttpHeadersSetter INSTANCE = new HttpHeadersSetter();

        @Override
        public void set(org.springframework.http.HttpHeaders carrier, String key, String value) {
            if (carrier != null) {
                carrier.add(key, value);
            }
        }
    }
}
