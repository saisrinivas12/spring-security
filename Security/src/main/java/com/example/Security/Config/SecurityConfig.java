package com.example.Security.Config;


import com.example.Security.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // http.authorizeRequests((request) -> request.antMatchers("/users").authenticated()).formLogin((login -> login.permitAll())).logout((logout) -> logout.permitAll());h
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/signin").permitAll()
                .antMatchers(HttpMethod.GET,"/public/**")//surpasses this endpoint meaning no login will be asked
                .hasRole("USER")
                .antMatchers("/users/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/users")
                .failureForwardUrl("/error")
                .and()
                .logout()
                .permitAll()
                .logoutUrl("/logoutpage");
    }

    //inmemory authentication

  /*  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("saisrinivas").password(this.passwordEncoder().encode("laddu")).roles("USER");
        auth.inMemoryAuthentication().withUser("charan").password(this.passwordEncoder().encode("charan")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("karthik").password(this.passwordEncoder().encode("karthik")).roles("ADMIN");
    }*/

    //authentication using MYSQL
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(this.passwordEncoder());
    }



@Bean
    public BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder(10);
    }
}
