package com.cstins.personal.api;

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
    public User getUser(@PathVariable("uid") Integer uid) {
        return userService.getUserById(uid);
    }

    @PutMapping("/user")
    public boolean updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/collection/{uid}/{aid}")
    public boolean collectionArticle(@PathVariable("uid") Integer uid, @PathVariable("aid") Integer aid) {
        return userService.collectionArticle(uid, aid);
    }

    @DeleteMapping("/collection/{uid}/{aid}")
    public boolean cancelCollectionArticle(@PathVariable("uid") Integer uid, @PathVariable("aid") Integer aid) {
        return userService.cancelCollection(uid, aid);
    }
}
