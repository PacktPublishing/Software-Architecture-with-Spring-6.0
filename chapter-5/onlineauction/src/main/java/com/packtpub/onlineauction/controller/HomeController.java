package com.packtpub.onlineauction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {
    @GetMapping("/")
    String homeRoot() {
        return "home";
    }

    @GetMapping("/home")
    String home() {
        return "home";
    }
}