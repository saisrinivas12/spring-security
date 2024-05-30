package com.example.JWTExample.Controller;


import com.example.JWTExample.entity.JwtRequest;
import com.example.JWTExample.entity.JwtResponse;
import com.example.JWTExample.helper.Jwthelper;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Jwthelper jwthelper;

    @Autowired
    private UserDetailsService userDetailsService;
private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @PostMapping("/login")
    public ResponseEntity<JwtResponse>login(@RequestBody JwtRequest request){
        if(this.doAuthenticate(request)){
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
          String jwtToken =  this.jwthelper.generateToken(userDetails);
           JwtResponse response = new JwtResponse(userDetails.getUsername(),jwtToken);
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }
        else{
            logger.error("Invalid credentials !!");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    public boolean doAuthenticate(JwtRequest request){
        Authentication auth = new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword());
       try {
           Authentication isAuthenticated = authenticationManager.authenticate(auth);
           logger.info("user authenticated "+isAuthenticated.isAuthenticated()+"request user "+request.getUserName());
           return isAuthenticated.isAuthenticated();
       }
       catch(AuthenticationException ex){
           logger.error("authentocationexception occurred",ex);
       }
         return false;
    }

    @ExceptionHandler(AuthenticationException.class)
    public String exceptions(){
        return "Credentials Invalid!!";
    }


}
