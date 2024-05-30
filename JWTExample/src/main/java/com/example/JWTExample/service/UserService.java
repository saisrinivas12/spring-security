package com.example.JWTExample.service;


import com.example.JWTExample.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    private List<User> users = new ArrayList<>();

    public List<User> getAllUsers(){
        users.add(new User("saisrinivas","saisrinivas374@gmail.com","sai","srinivas"));
        users.add(new User("charan","charan@gmail.com","charan","bonagiri"));
        return users;
    }

    public User addUser(User user){
        users.add(user);
        return user;
    }
}
