package com.simple.tracing.servicea.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Map;

@RestController
public class Helloworld {

    private static final Log logger = LogFactory.getLog(Helloworld.class);

    @RequestMapping("/service-c")
    String home(@RequestHeader Map<String, String> headers) {
        logger.info("home() has been called");
        headers.forEach((key, value) -> System.out.println(key + ": " + value));

        return "Hello World from service C";
    }

}
