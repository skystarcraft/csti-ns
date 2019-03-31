package com.cstins.front.api;

import com.alibaba.fastjson.JSONObject;
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
    public JSONObject getIntroduction() {
        Front front = frontService.findFront();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "获取成功！");
        jsonObject.put("data", front);
        return jsonObject;
    }

    @PutMapping(value = "/about")
    public JSONObject updateIntroduction(@RequestBody Front front) {
        boolean b = frontService.updateFront(front);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "更新成功！");
            jsonObject.put("data", front);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "更新失败！");
            jsonObject.put("data", front);
        }
        return jsonObject;
    }
}
