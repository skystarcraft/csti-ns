package com.cstins.manager.service;

import com.cstins.manager.entity.User;
import com.cstins.manager.tools.JsonDateValueProcessor;
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
public class LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 登录
     * @param uid
     * @param password
     * @return
     */
    public String userLogin(Integer uid, String password) {
        if (userService.userIsExists(uid)) {
            User user = userService.getUserByUid(uid);
            if (user.getUser_password().equals(password) && user.getType() >= 3) {
                String token = UUID.randomUUID().toString();
                user.setUser_password(null);
                new JsonConfig().registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
                JSONObject jsonObject = JSONObject.fromObject(user);
                redisTemplate.opsForValue().set("SESSION:" + token, jsonObject.toString());
                redisTemplate.expire("SESSION:" + token, 15 * 60, TimeUnit.SECONDS);    //15分钟过期
                return token;
            } else {
                return "权限不够！";
            }
        }
        return "用户不存在！";
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
            redisTemplate.expire("SESSION:" + token, 15 * 60, TimeUnit.SECONDS);    //重置过期时间
            return user;
        }
        return null;
    }

}
