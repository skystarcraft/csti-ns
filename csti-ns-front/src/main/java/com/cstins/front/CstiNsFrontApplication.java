package com.cstins.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.cstins.front.dao")
@EntityScan("com.cstins.front.entity")
@ComponentScan(value = {"com.cstins.front.dao", "com.cstins.front.service", "com.cstins.front.api"})
public class CstiNsFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(CstiNsFrontApplication.class, args);
    }

}
