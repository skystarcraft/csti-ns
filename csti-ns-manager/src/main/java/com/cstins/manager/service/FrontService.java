package com.cstins.manager.service;

import com.cstins.manager.dao.FrontDao;
import com.cstins.manager.entity.Front;
import com.cstins.manager.tools.JsonDateValueProcessor;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

/**
 * @program: csti-ns
 * @description: 前台相关服务类
 * @author: 杨云龙
 **/

@Service
@Transactional
public class FrontService {

    private final static String FRONTINFO = "csti:front";

    @Autowired
    private FrontDao frontDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 更新前台显示数据,同时更新Redis
     * @param front
     * @return
     */
    public boolean updateFront(Front front) {
        if (front == null) return false;
        try {
            frontDao.save(front);
            JSONObject jsonObject = JSONObject.fromObject(front);
            redisTemplate.opsForHash().put(FRONTINFO, "info", jsonObject.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取前台显示数据，先查找Redis
     * @return
     */
    public Front findFront() {
        Object info = redisTemplate.opsForHash().get(FRONTINFO, "info");
        if (info != null && !"".equals(info)) {
            JSONObject jsonObject = JSONObject.fromObject(info);
            Front front = (Front) JSONObject.toBean(jsonObject, Front.class);
            return front;
        } else {
            Optional<Front> front = frontDao.findById(1);
            new JsonConfig().registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
            JSONObject jsonObject = JSONObject.fromObject(front.get());
            redisTemplate.opsForHash().put(FRONTINFO, "info", jsonObject.toString());
            return front.get();
        }
    }
}
