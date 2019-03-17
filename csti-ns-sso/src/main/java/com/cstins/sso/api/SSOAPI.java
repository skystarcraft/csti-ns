package com.cstins.sso.api;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@RestController
public class SSOAPI {

    @GetMapping("/user")
    public Authentication getUser(Authentication authentication) {
        System.err.println("resource: user " + authentication);
        return authentication;
    }
}
