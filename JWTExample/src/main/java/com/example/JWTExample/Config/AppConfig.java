package com.example.JWTExample.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {

/*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests((auth)-> auth.anyRequest().authenticated());

        return http.build();
    }*/

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.builder().username("saisrinivas").password(this.passwordEncoder().encode("Ravikanth")).roles("ADMIN").build();
        UserDetails user2 = User.builder().username("charan").password(this.passwordEncoder().encode("charan")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user1,user2);

    }

    @Bean

    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationManager builder(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }
}
