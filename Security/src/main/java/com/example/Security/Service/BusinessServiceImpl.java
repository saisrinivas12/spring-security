package com.example.Security.Service;

import com.example.Security.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class BusinessServiceImpl implements BusinessService{


    private static final List<User> users = new ArrayList<>();


    @PostConstruct
    public void init(){
        users.add(new User("skullex","sai srinivas","sai","laddu"));
        users.add(new User("gp","kar","kar","thik"));
        System.out.println("users are added");
    }
    @PreDestroy
    public void destroy(){
        users.clear();
    }
    @Override
    public ResponseEntity<User> Create(User user) {
        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Override
    public ResponseEntity<List<User>> getUserDetails() {
       return ResponseEntity.status(HttpStatus.ACCEPTED).body(users);
    }

    @Override
    public ResponseEntity<User> getUserBasedOnId(String userId) {
        User requiredUser = null;
        for(User user : users){
            if(user.getUserId().equals(userId)){
                requiredUser = user;
            }
        }

        if(requiredUser!=null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(requiredUser);
        }
        return ResponseEntity.notFound().build();
    }
}
