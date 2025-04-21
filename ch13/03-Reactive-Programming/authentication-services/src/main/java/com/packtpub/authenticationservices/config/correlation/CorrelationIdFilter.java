package com.packtpub.authenticationservices.config.correlation;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class CorrelationIdFilter implements WebFilter {

    private static final String CORRELATION_ID_HEADER_NAME = "x-correlation-id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // Get the correlation ID from the request header or generate a new one
        String correlationId = request.getHeaders().getFirst(CORRELATION_ID_HEADER_NAME);
        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = UUID.randomUUID().toString();
        }

        // Add the correlation ID to the response header
        response.getHeaders().add(CORRELATION_ID_HEADER_NAME, correlationId);

        // Optionally store the correlation ID in a reactive context
        String finalCorrelationId = correlationId;
        return chain.filter(exchange)
                .contextWrite(context -> context.put(CORRELATION_ID_HEADER_NAME, finalCorrelationId));
    }
}