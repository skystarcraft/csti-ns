package com.cstins.front.api;

import com.cstins.front.service.NaxinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/
@RestController
public class NaxinAPI {

    @Autowired
    private NaxinService naxinService;

    @GetMapping("/front/naxin/{uid}")
    public boolean naxin(@PathVariable("uid") Integer uid) {
        return naxinService.naxin(uid);
    }

}
