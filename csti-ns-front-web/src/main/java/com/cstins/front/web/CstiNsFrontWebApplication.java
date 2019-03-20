package com.cstins.front.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@EnableEurekaClient
@EnableOAuth2Sso
@EnableDiscoveryClient
@EntityScan("com.cstins.sso.entity")
@ComponentScan(value = {"com.cstins.front.web.config"})
public class CstiNsFrontWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CstiNsFrontWebApplication.class, args);
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
