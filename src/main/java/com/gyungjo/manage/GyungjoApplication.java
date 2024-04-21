package com.gyungjo.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GyungjoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GyungjoApplication.class, args);
    }

}
