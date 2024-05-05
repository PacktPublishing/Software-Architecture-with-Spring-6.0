package com.packtpub.onlineauction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class ProductController {

    @GetMapping("/products")
    public String products() {
        return "product-list";
    }
}