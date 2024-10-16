package com.simple.tracing.servicea.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@RestController
public class Helloworld {

    private static final Log logger = LogFactory.getLog(Helloworld.class);

    private final RestTemplate restTemplate;
    private final RestClient.Builder restClientBuilder;

    public Helloworld(RestTemplate restTemplate, RestClient.Builder restClientBuilder) {
        this.restTemplate = restTemplate;
        this.restClientBuilder = restClientBuilder;
    }

    @GetMapping("/")
    public ResponseEntity path1() {

        logger.info("Incoming request at {} for request /path1 ");
        String response = restTemplate.getForObject("http://localhost:8081/service-b", String.class);
        return ResponseEntity.ok("response from /path1 + " + response);
    }

    @GetMapping("/restClient")
    public ResponseEntity path1RestClient() {

        logger.info("Incoming request at {} for request /path1 ");
        String response =  restClientBuilder.build()
                .get()
                .uri("http://localhost:8081/service-b/restClient")
                .retrieve()
                .body(String.class);
        return ResponseEntity.ok("response from /pathA + " + response);
    }

//    @RequestMapping("/restClient")
//    String home() {
//        logger.info("home() has been called");
////        Span echoSpan = tracer.nextSpan().name("calling-echo").start();
//
//        String result = restClient.build()
//                .get()
//                .uri("http://localhost:8081/service-b")
//                .retrieve()
//                .body(String.class);
//
////        echoSpan.end();
//
//
//        return result;
//
//    }
}



//
////    @Autowired
////    Tracer tracer;
//
//    private final RestClient.Builder restClient;
//
//    public Helloworld(RestClient.Builder restClient) {
//        this.restClient = restClient;
//    }
//
//
//    @RequestMapping("/")
//    String home() {
//        logger.info("home() has been called");
////        Span echoSpan = tracer.nextSpan().name("calling-echo").start();
//
//        String result = restClient.build()
//                .get()
//                .uri("http://localhost:8081/service-b")
//                .retrieve()
//                .body(String.class);
//
////        echoSpan.end();
//
//
//        return result;
//
//    }

//}
