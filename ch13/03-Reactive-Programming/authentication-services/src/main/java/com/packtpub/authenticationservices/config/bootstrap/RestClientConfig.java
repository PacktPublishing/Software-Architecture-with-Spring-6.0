package com.packtpub.authenticationservices.config.bootstrap;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestClientConfig {

//    @LoadBalanced
//    @Bean
//    public RestClient.Builder restClient(RestTemplateBuilder builder) {
//        return RestClient.builder(builder.build());
//    }

    @LoadBalanced
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
