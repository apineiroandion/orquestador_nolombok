package com.dam.orquestador_nolombok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrquestadorNolombokApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrquestadorNolombokApplication.class, args);
    }

}
