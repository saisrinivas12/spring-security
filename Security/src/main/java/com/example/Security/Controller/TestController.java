package com.example.Security.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {


    @GetMapping("/signin")
    public String loginPage(){
        return "login.html";
    }


    @GetMapping("/successLogout")
    public String logout(){
        return "successlogout.html";
    }

    @GetMapping("/logoutpage")
    public String  logoutpage(){
        return "logoutpage.html";
    }
}
