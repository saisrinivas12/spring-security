package com.example.Security.Service;

import com.example.Security.DbUsers;
import com.example.Security.repo.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetailsRepository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DbUsers user = repo.findByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("USER NAME NOT FOUND!!");

        }
        return new CustomUserDetails(user);
    }
}
