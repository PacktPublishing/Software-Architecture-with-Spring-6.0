package com.packtpub.gatewayservices.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class AddCorrelationIdGlobalPreFilter implements GlobalFilter {

    final Logger logger = LoggerFactory.getLogger(AddCorrelationIdGlobalPreFilter.class);
    private static final String CORRELATION_ID = "x-correlation-id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String correlationId = exchange.getRequest().getHeaders().getFirst("x-correlation-id");
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
            exchange.getRequest().mutate().header(CORRELATION_ID, correlationId ).build();
            logger.info("Correlation Id: {}", correlationId);
        }
        return chain.filter(exchange);
    }
}
