package com.packtpub.onlineauction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/validate")
    public String validate() {
        return "home";
    }

//    @GetMapping("/login")
//    public String login(@RequestParam(value = "error", required = false) String error,
//                        @RequestParam(value = "logout", required = false) String logout,
//                        Model model) {
//        if (error != null) {
//            model.addAttribute("error", "Your username and password are invalid.");
//        }
//
//        if (logout != null) {
//            model.addAttribute("message", "You have been logged out successfully.");
//        }
//
//        return "login";
//    }

//    @GetMapping("/")
//    public String hello(){
//        return "hello";
//    }
//
//    @GetMapping("/message")
//    public String message(Model model) {
//        model.addAttribute("message", "This is a custom message");
//        return "message";
//    }

}