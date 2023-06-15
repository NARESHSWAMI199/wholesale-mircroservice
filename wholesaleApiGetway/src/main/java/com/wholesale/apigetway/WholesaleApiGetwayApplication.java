package com.wholesale.apigetway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WholesaleApiGetwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WholesaleApiGetwayApplication.class, args);
	}

}
