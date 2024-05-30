package com.example.Security.Controller;


import com.example.Security.Service.BusinessService;
import com.example.Security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private BusinessService businessService;


    @PostMapping("/save")
    public ResponseEntity<User> create(@RequestBody User user){
        return businessService.Create(user);
    }

    @GetMapping
    public ResponseEntity<List<User>>getAllUsers(){
        return businessService.getUserDetails();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable(name="userId") String userId){
        return businessService.getUserBasedOnId(userId);
    }
}
