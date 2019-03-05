package com.cstins.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.cstins.personal.dao")
@EntityScan("com.cstins.personal.entity")
@ComponentScan(value = {"com.cstins.personal.dao", "com.cstins.personal.service", "com.cstins.personal.api"})
public class CstiNsPersonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CstiNsPersonalApplication.class, args);
    }

}
