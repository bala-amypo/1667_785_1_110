package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("12345"));

		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		if(encoder.matches("12345","$2a$10$1MF.hoQy3nFqNTnPpeKHPOaNF162JoAZDw5SO97BN8hxRFsELnwti")){
			System.err.println("Password Matched");
		}
		else{
			System.out.print("No match");
		}
	}

}
