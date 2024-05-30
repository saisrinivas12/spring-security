package com.example.JWTExample.Controller;

import com.example.JWTExample.entity.User;
import com.example.JWTExample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/protected")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> getAllUsers(){

        return service.getAllUsers();
    }
}
