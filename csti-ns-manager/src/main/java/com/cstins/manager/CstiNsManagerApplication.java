package com.cstins.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.cstins.manager.dao")
@EntityScan("com.cstins.manager.entity")
@ComponentScan(value = {"com.cstins.manager.dao", "com.cstins.manager.service", "com.cstins.manager.api"})
public class CstiNsManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CstiNsManagerApplication.class, args);
    }

}
