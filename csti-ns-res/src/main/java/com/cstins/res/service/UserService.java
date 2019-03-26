package com.cstins.res.service;

import com.cstins.res.dao.UserDao;
import com.cstins.res.entity.User;
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

    public boolean reduceScore(Integer score, Integer uid) {
        try {
            userDao.reduce_points(score, uid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
