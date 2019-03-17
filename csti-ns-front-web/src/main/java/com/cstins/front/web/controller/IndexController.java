package com.cstins.front.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public Authentication getUser(Authentication authentication) {
        return authentication;
    }

}
