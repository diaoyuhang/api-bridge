package com.api.bridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Transactional
public class ApiBridgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiBridgeApplication.class, args);
    }

}
