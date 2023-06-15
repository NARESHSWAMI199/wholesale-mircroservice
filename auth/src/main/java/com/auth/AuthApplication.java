package com.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients


public class AuthApplication extends ServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
