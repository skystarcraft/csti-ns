package com.cstins.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 资源服务器、认证服务器
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.cstins.sso.dao")
@EntityScan("com.cstins.sso.entity")
@ComponentScan(value = {"com.cstins.sso.dao", "com.cstins.sso.service", "com.cstins.sso.api","com.cstins.sso.tools"})
public class CstiNsSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CstiNsSsoApplication.class, args);
    }
}
