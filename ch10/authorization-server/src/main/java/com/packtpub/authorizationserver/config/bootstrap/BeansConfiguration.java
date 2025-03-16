package com.packtpub.authorizationserver.config.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class BeansConfiguration {

    @Bean
    public RestClient.Builder restClient() {
        return RestClient.builder();
    }

}