package com.example.JWTExample.Config;

import com.example.JWTExample.entrypoint.JwtAuthenticationEntryPoint;
import com.example.JWTExample.filter.JwtAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;


    @Autowired
    private JwtAuthenticationFilter filter;


    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
/*

        http.cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request.requestMatchers("/protected/**")
                        .authenticated()
                        .requestMatchers("/auth/login")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(entryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();*/


        http.csrf(csrf-> csrf.disable())
                .cors(cors->cors.disable())
                .authorizeHttpRequests(request -> request.requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/protected/**").authenticated()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(entryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }




}
