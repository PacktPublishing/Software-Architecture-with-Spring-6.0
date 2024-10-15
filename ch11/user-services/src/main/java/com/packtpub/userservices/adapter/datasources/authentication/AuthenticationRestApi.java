package com.packtpub.userservices.adapter.datasources.authentication;

import com.packtpub.userservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
import com.packtpub.userservices.config.correlation.CorrelationIdUtil;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapSetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationRestApi {

//    private final RestClient restClient;
//
//    private final DiscoveryClient discoveryClient;

    private final RestClient.Builder restClient;


    public boolean validateToken(String token) {

        Boolean result = restClient.build()
                .get()
                .uri(URI.create("http://AUTHENTICATION-SERVICES" + "/v1/api/auth/validate?token=" + token))
                .headers(httpHeaders -> {
                    // Propagate trace context
                    Context currentContext = Context.current();
                    GlobalOpenTelemetry.getPropagators().getTextMapPropagator()
                            .inject(currentContext, httpHeaders, HttpHeadersSetter.INSTANCE);

                    // Custom correlation ID (optional)
                    httpHeaders.add("x-correlation-id", CorrelationIdUtil.getCorrelationId());
                })
                .retrieve()
                .body(Boolean.class);
        log.info("Outgoing traceparent: {}", Span.current().getSpanContext().getTraceId());
        return result;

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
