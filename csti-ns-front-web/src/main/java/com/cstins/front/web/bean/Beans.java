package com.cstins.front.web.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/
@Configuration
public class Beans {

    @Bean
    public RestTemplate getRestTemplete() {
        return new RestTemplate();
    }
}
