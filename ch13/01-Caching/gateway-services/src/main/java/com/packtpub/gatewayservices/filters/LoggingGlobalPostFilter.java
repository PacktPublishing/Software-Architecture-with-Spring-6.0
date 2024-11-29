package com.packtpub.gatewayservices.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Configuration
public class LoggingGlobalPostFilter {

    final Logger logger = LoggerFactory.getLogger(LoggingGlobalPostFilter.class);

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        logger.info("Returning request URI: {} - Header: x-correlation-id: {} ", exchange.getRequest().getURI(), exchange.getRequest().getHeaders().get("x-correlation-id"));
                    }));
        };
    }
}

