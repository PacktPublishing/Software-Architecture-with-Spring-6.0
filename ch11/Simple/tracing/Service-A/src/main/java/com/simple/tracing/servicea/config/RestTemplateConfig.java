//package com.simple.tracing.servicea.config;
//
//import io.micrometer.observation.ObservationRegistry;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@Configuration
//public class RestTemplateConfig {
//
//    @Bean
//    public RestTemplate restTemplate(ObservationRegistry observationRegistry) {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setInterceptors(List.of(
//                new ObservationRestTemplateInterceptor(observationRegistry)
//        ));
//        return restTemplate;
//    }
//}