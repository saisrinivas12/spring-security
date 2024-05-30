package com.example.Security.Service;

import com.example.Security.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BusinessService {


    ResponseEntity<User> Create(User user);


    ResponseEntity<List<User>>getUserDetails();


    ResponseEntity<User>getUserBasedOnId(String userId);
}
