package com.cstins.personal.api;

import com.cstins.personal.entity.User;
import com.cstins.personal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
