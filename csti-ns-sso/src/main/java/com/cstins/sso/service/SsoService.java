package com.cstins.sso.service;

import com.cstins.sso.entity.User;
import com.cstins.sso.tools.JsonDateValueProcessor;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@Service
public class SsoService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param uid
     * @param password
     * @return
     */
    public String userLogin(Integer uid, String password) {
        if (userService.userIsExists(uid)) {
            User user = userService.getUserByUid(uid);
            if (user.getUser_password().equals(password)) {
                String token = UUID.randomUUID().toString();
                user.setUser_password(null);
                new JsonConfig().registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
                JSONObject jsonObject = JSONObject.fromObject(user);
                redisTemplate.opsForValue().set("SESSION:" + token, jsonObject.toString());
                redisTemplate.expire("SESSION:" + token, 30 * 60, TimeUnit.SECONDS);    //30分钟过期
                return token;
            }
        }
        return "failed";
    }

    public String userLogout(String token) {
        if (redisTemplate.hasKey("SESSION:" + token) && redisTemplate.opsForValue().get("SESSION:" + token) != null) {
            redisTemplate.delete("SESSION:" + token);
            return "success";
        }
        return "failed";
    }

    /**
     * 获取token
     * @param token
     * @return
     */
    public User getUserByToken(String token) {
        String result = redisTemplate.opsForValue().get("SESSION:" + token);
        if (StringUtils.isNotBlank(result) && StringUtils.isNotEmpty(result)) {
            JSONObject jsonObject = JSONObject.fromObject(result);
            User user = (User) JSONObject.toBean(jsonObject, User.class);
            redisTemplate.expire("SESSION:" + token, 30 * 60, TimeUnit.SECONDS);    //重置过期时间
            return user;
        }
        return null;
    }

    /**
     * 更新token
     * @param token
     * @return
     */
    public User updateToken(String token) {
        String result = redisTemplate.opsForValue().get("SESSION:" + token);
        if (StringUtils.isNotBlank(result) && StringUtils.isNotEmpty(result)) {
            JSONObject jsonObject = JSONObject.fromObject(result);
            User user = (User) JSONObject.toBean(jsonObject, User.class);
            User updateUser = userService.getUserByUid(user.getUid());
            updateUser.setUser_password("");
            new JsonConfig().registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
            JSONObject js = JSONObject.fromObject(updateUser);
            redisTemplate.opsForValue().set("SESSION:" + token, js.toString());
            redisTemplate.expire("SESSION:" + token, 30 * 60, TimeUnit.SECONDS);    //重置过期时间
            return user;
        }
        return null;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    public boolean register(User user) {
        if (!userService.userIsExists(user.getUid())) {
            user.setType(0);
            user.setUintegral(0);
            user.setUser_image("http://134.175.68.126:9999/group1/M00/00/00/rBAADVyRvN6AVscIAAFDbgtDc8k699.jpg");
            user.setUser_pay(0);
            user.setUser_payimage(null);
            try {
                userService.addUser(user);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
