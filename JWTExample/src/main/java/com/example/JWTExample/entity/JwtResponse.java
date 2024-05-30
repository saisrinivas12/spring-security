package com.example.JWTExample.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class JwtResponse {

    private String userName;

    private String jwtToken;

    public JwtResponse(String userName, String jwtToken) {
        this.userName = userName;
        this.jwtToken = jwtToken;
    }

    public JwtResponse(){}

}
