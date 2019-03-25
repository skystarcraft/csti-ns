package com.cstins.manager.service;

import com.cstins.manager.dao.UserDao;
import com.cstins.manager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public String getPasswordByUid(Integer uid) {
        return userDao.findByUidEquals(uid).getUser_password();
    }

    public boolean userIsExists(Integer uid) {
        return userDao.existsByUidEquals(uid);
    }

    public User getUserByUid(Integer uid) {
        return userDao.findById(uid).get();
    }

    public void addUser(User user) {
        userDao.save(user);
    }
}
