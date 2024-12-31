package com.packtpub.authenticationservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class AuthenticationServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServicesApplication.class, args);
	}

}
