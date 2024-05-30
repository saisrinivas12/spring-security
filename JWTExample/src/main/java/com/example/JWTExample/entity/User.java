package com.example.JWTExample.entity;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class User {

    private String userName;

    private String email;

    private String firstName;

    private String lastName;

    public User(String userName, String email, String firstName, String lastName) {
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(){

    }
}
