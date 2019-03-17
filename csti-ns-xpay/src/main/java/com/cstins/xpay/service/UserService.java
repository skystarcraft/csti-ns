package com.cstins.xpay.service;

import com.cstins.xpay.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    /**
     * 用户充值
     * @param uintegral
     * @param uid
     * @return
     */
    public boolean addUintegral(Integer uintegral, Integer uid) {
        try {
            userDao.addScore(uintegral, uid);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

}
