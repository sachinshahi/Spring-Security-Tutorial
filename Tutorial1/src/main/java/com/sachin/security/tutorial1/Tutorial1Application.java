package com.sachin.security.tutorial1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
public class Tutorial1Application {

    public static void main(String[] args) {
        SpringApplication.run(Tutorial1Application.class, args);
    }

}
