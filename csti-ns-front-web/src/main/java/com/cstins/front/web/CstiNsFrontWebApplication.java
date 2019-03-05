package com.cstins.front.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CstiNsFrontWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CstiNsFrontWebApplication.class, args);
    }

}
