package com.cstins.manager.service;

import com.cstins.manager.dao.LinkDao;
import com.cstins.manager.entity.Link;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: csti-ns
 * @description: 友情链接相关服务
 * @author: 杨云龙
 **/

@Service
@Transactional
public class LinkService {

    private final static String REDISKEY = "csti:front";

    @Autowired
    private LinkDao linkDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public List<Link> getLinks() {
        List<Link> list = null;
        Object links = redisTemplate.opsForHash().get(REDISKEY, "links");
        if (links != null && !"".equals(links) && !"null".equals(links)) {
            list = new ArrayList<>();
            JSONArray jsonArray = JSONArray.fromObject(links);
            for (int i = 0; i < jsonArray.size(); i++) {
                Link l = (Link) JSONObject.toBean((JSONObject) jsonArray.get(i), Link.class);
                list.add(l);
            }
        } else {
            list = linkDao.findAll();
//            list = linkDao.findByTypeEquals(0);
            JSONArray jsonArray = JSONArray.fromObject(list);
            redisTemplate.opsForHash().put(REDISKEY, "links", jsonArray.toString());
        }
        return list;
    }

    public boolean addLink(Link link) {
        if (link == null) return false;
        try {
            linkDao.addLink(link.getLink_name(), link.getLink_addr());
            redisTemplate.opsForHash().delete(REDISKEY, "links");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean updateLink(Link link) {
        if (link == null) return false;
        try {
            linkDao.updateLink(link.getLink_name(), link.getLink_addr(), link.getId());
            redisTemplate.opsForHash().delete(REDISKEY, "links");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delLnk(Link link) {
        if (link == null) return false;
        try {
            linkDao.delete(link);
            redisTemplate.opsForHash().delete(REDISKEY, "links");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delLnk(Integer id) {
        try {
            Link link = linkDao.findById(id).get();
            linkDao.delete(link);
            redisTemplate.opsForHash().delete(REDISKEY, "links");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delLinks(String[] link) {
        if (link == null) return false;
        try {
            List<String> list = Arrays.asList(link);
            list.forEach(l -> {
                linkDao.delete(linkDao.findById(Integer.parseInt(l)).get());
            });
            redisTemplate.opsForHash().delete(REDISKEY, "links");
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
