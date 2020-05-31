package com.example.springwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
public class SpringWebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebserviceApplication.class, args);
    }

}
