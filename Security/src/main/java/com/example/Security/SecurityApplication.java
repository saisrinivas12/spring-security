package com.example.Security;

import com.example.Security.repo.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {

	@Autowired
	private UserDetailsRepository repo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Bean
	public PasswordEncoder encoder(){
		//System.out.println("encode "+new BCryptPasswordEncoder().encode("laddu"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode("laddu");
		System.out.println("encode "+result);
		boolean res = encoder.matches("laddu",result);
		System.out.println("res "+res);
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
     DbUsers user1 = new DbUsers();
	 user1.setUserName("saisrinivas");
	 user1.setEmail("saisrinivas374@gmail.com");
	 user1.setRole("ROLE_USER");
	 user1.setPassword(passwordEncoder.encode("Ravikanth"));

	 repo.save(user1);
	 System.out.println("successfully saved the user "+user1);

	 DbUsers user2 = new DbUsers();
	 user2.setUserName("charan");
	 user2.setEmail("charan@gmail.com");
	 user2.setPassword(passwordEncoder.encode("charan"));
	 user2.setRole("ROLE_ADMIN");
	 repo.save(user2);
	 System.out.println("successfully saved the user "+user1);



	}
}
