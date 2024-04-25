package com.gyungjo.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class GyungjoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GyungjoApplication.class, args);
    }

    @Bean
    public AuditorAware<String> auditorProvider(){
        //TODO. 스프링 시큐리티 적용시 SecurityContext 로부터 로그인한 유저의 정보를 얻어서 리턴하도록 해야 한다.

        return () -> Optional.of(UUID.randomUUID().toString());
    }

}
