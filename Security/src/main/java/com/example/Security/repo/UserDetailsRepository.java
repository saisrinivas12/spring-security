package com.example.Security.repo;

import com.example.Security.DbUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<DbUsers,String> {


    public DbUsers findByUserName(String userName);
}
