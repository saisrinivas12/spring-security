package com.example.springsecurity.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api")
public class AuthController {


    @GetMapping("/getMessage")
    public String getMessage(){
        return "Hi Welcome to spring security";
    }

    @GetMapping("/home")
    public String getHome(){
        return "Hi Welcome to Home";
    }

    @GetMapping("/")
    public String get(){
        return "Hi ";
    }
}
