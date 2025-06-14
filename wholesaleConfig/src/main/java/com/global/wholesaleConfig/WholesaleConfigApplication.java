package com.global.wholesaleConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
/*
	The @EnableConfigServer annotation in Spring Cloud is a powerful annotation that transforms a standard Spring Boot application into a Spring Cloud Config Server.
	Spring Cloud Config Server solves this problem by providing a centralized externalized configuration service. Instead of each microservice having its own application.properties or application.yml files, they fetch their configurations from the Config Server at runtime.
 
*/
	
public class WholesaleConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(WholesaleConfigApplication.class, args);
	}

}
