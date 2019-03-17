package com.cstins.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 资源服务器、认证服务器
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.cstins.sso.dao")
@EntityScan("com.cstins.sso.entity")
@ComponentScan(value = {"com.cstins.sso.dao", "com.cstins.sso.service", "com.cstins.sso.api","com.cstins.sso.tools"})
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@EnableAuthorizationServer
@EnableResourceServer
public class CstiNsSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CstiNsSsoApplication.class, args);
    }

}
