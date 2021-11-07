package com.Back_Gr42_Eq03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BackGr42Eq03Application {

	public static void main(String[] args) {
		SpringApplication.run(BackGr42Eq03Application.class, args);
	}
}