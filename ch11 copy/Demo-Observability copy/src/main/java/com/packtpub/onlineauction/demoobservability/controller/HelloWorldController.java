package com.packtpub.onlineauction.demoobservability.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloWorldController {

    @GetMapping("/v1/helloWorld")
    public String sayHello() {
        log.info("Hello World my friend");
        return "Hello World!";
    }


}
