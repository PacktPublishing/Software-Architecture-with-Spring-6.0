package com.packtpub.gatewayservices.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

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

