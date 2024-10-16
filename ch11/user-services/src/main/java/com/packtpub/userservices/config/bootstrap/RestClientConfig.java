package com.packtpub.userservices.config.bootstrap;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient.Builder restClient(RestTemplateBuilder builder) {
        return RestClient.builder(builder.build());
    }

}
