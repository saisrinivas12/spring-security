package com.example.springsecurity.WebsecurityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvc  implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        //registry.addViewController("/login").setViewName("login");
        registry.addViewController("/Hello").setViewName("Hello");
    }

}
