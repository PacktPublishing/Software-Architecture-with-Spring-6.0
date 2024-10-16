package com.simple.tracing.servicea.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class Helloworld {

    private static final Log logger = LogFactory.getLog(Helloworld.class);

    private final RestTemplate restTemplate;
    private final RestClient.Builder restClientBuilder;

    public Helloworld(RestTemplate restTemplate, RestClient.Builder restClientBuilder) {
        this.restTemplate = restTemplate;
        this.restClientBuilder = restClientBuilder;
    }
    @GetMapping("/service-b")
    public ResponseEntity path1() {

        logger.info("Incoming request at {} for request /path1 ");
        String response = restTemplate.getForObject("http://localhost:8082/service-c", String.class);
        return ResponseEntity.ok("response from /path1 + " + response);
    }

    @GetMapping("/service-b/restClient")
    public ResponseEntity path1RestClient() {

        logger.info("Incoming request at {} for request /path1 ");
        String response =  restClientBuilder.build()
                .get()
                .uri("http://localhost:8082/service-c")
                .retrieve()
                .body(String.class);
        return ResponseEntity.ok("response from /pathB + " + response);
    }
}

//@RestController
//public class Helloworld {
//
//    private static final Log logger = LogFactory.getLog(Helloworld.class);
//
////    private final RestClient.Builder restClient;
////
////    public Helloworld(RestClient.Builder restClient) {
////        this.restClient = restClient;
////    }
////
////    @RequestMapping("/service-b")
////    String home(@RequestHeader Map<String, String> headers) {
////        logger.info("home() has been called");
////        headers.forEach((key, value) -> System.out.println(key + ": " + value));
////
//////        String result = restClient.build()
//////                .get()
//////                .uri("http://localhost:8082/service-c")
//////                .retrieve()
//////                .body(String.class);
////        return  "Hello World from service B - ";
////    }
//
//}
