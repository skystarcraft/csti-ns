package com.cstins.front.api;

import com.cstins.front.entity.Front;
import com.cstins.front.service.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: csti-ns
 * @description: 对表现层提供的API接口
 * @author: 杨云龙
 **/

@RestController
public class FrontAPI {

    @Autowired
    private FrontService frontService;

    @GetMapping("/about")
    public Front getIntroduction() {
        return frontService.findFront();
    }

    @PutMapping(value = "/about")
    public boolean updateIntroduction(@RequestBody Front front) {
        if (front == null) return false;
        return frontService.updateFront(front);
    }
}
