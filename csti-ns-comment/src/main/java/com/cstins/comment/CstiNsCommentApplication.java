package com.cstins.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.cstins.comment.dao")
@EntityScan("com.cstins.comment.entity")
@ComponentScan(value = {"com.cstins.comment.dao", "com.cstins.comment.service", "com.cstins.comment.api"})
public class CstiNsCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CstiNsCommentApplication.class, args);
    }

}
