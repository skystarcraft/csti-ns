package com.cstins.personal.service;

import com.cstins.personal.dao.UserDao;
import com.cstins.personal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: csti-ns
 * @description: 用户相关服务
 * @author: 杨云龙
 **/

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    List<User> getUsers() {
        return userDao.findAllByTypeEquals(2);
    }

}
