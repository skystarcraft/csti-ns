package com.cstins.personal.service;

import com.cstins.personal.dao.CollectionDao;
import com.cstins.personal.dao.UserDao;
import com.cstins.personal.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @program: csti-ns
 * @description: 用户相关服务
 * @author: 杨云龙
 **/

@Service
public class UserService {

    private final static Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private CollectionDao dao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String REDISKEY = "csti:ns";

    /**
     * 查询所有组内成员
     * @return
     */
    public List<User> getUsers() {
        return userDao.findAllByTypeEquals(2);
    }

    public List<User> getAllUser() {
        return userDao.findAll();
    }

    public User getUserById(Integer uid) {
        return userDao.findById(uid).get();
    }

    public boolean delUsers(String[] uid) {
        try {
            List<String> list = Arrays.asList(uid);
            list.forEach(user -> {
                userDao.delete(getUserById(Integer.parseInt(user)));
            });
            redisTemplate.opsForHash().delete(REDISKEY, "user");
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    public boolean delUser(Integer uid) {
        try {
            userDao.delete(userDao.findById(uid).get());
            redisTemplate.opsForHash().delete(REDISKEY, "user");
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    public boolean updateUser(User user) {
        try {
            userDao.save(user);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    public boolean collectionArticle(Integer uid, Integer aid) {
        try{
            dao.collectionArticle(uid, aid);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    public boolean cancelCollection(Integer uid, Integer aid) {
        try{
            dao.cancelCollection(uid, aid);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

}
