package com.cstins.personal.api;

import com.alibaba.fastjson.JSONObject;
import com.cstins.personal.entity.User;
import com.cstins.personal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: csti-ns
 * @description: 对外开放接口
 * @author: 杨云龙
 **/

@RestController
public class UserAPI {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{uid}")
    public JSONObject getUser(@PathVariable("uid") Integer uid) {
        User user = userService.getUserById(uid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "获取成功！");
        jsonObject.put("data", user);
        return jsonObject;
    }

    @PutMapping("/user")
    public JSONObject updateUser(@RequestBody User user) {
        boolean b = userService.updateUser(user);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "更新成功！");
            jsonObject.put("data", user);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "更新失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @GetMapping("/collection/{uid}/{aid}")
    public JSONObject collectionArticle(@PathVariable("uid") Integer uid, @PathVariable("aid") Integer aid) {
        boolean b = userService.collectionArticle(uid, aid);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "收藏成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "收藏失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @DeleteMapping("/collection/{uid}/{aid}")
    public JSONObject cancelCollectionArticle(@PathVariable("uid") Integer uid, @PathVariable("aid") Integer aid) {
        boolean b = userService.cancelCollection(uid, aid);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "取消收藏成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "取消收藏失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }
}
