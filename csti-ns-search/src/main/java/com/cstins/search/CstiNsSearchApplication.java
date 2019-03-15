package com.cstins.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.cstins.search.dao")
@EntityScan("com.cstins.search.entity")
@ComponentScan(value = {"com.cstins.search.dao", "com.cstins.search.service", "com.cstins.search.api","com.cstins.search.tools"})
public class CstiNsSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(CstiNsSearchApplication.class, args);
    }

}
