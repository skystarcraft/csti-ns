package com.cstins.front.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@RestController
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping
    public String showIndex() {
        return restTemplate.getForObject("http://localhost:8080/about", String.class);
    }

}
