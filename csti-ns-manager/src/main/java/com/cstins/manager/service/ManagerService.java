package com.cstins.manager.service;

import com.cstins.manager.dao.ArticleDao;
import com.cstins.manager.dao.TagsDao;
import com.cstins.manager.dao.UserDao;
import com.cstins.manager.entity.Article;
import com.cstins.manager.entity.Tags;
import com.cstins.manager.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @program: csti-ns
 * @description: 用户相关服务
 * @author: 杨云龙
 **/

@Service
@Transactional
public class ManagerService {

    private final static Logger logger = LogManager.getLogger(ManagerService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private TagsDao tagsDao;

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


    /**
     * 提权为组内成员
     * @param users
     * @return
     */
    public boolean Elevate_permissions(String[] users) {
        try {
            List<String> list = Arrays.asList(users);
            list.forEach(user -> {
                userDao.changeType(getUserById(Integer.parseInt(user)).getUid());
            });
            redisTemplate.opsForHash().delete(REDISKEY, "user");
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    /**
     * 提权为组内成员
     * @return
     */
    public boolean Elevate_permissions(Integer uid) {
        try {
            userDao.changeType(uid);
            redisTemplate.opsForHash().delete(REDISKEY, "user");
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    public List<Article> getArticles() {
        return articleDao.findAll();
    }

    /**
     * 删除文章
     * @param aid
     * @return
     */
    public boolean delArticle(Integer aid) {
        try {
            articleDao.delete(articleDao.findById(aid).get());
            redisTemplate.opsForHash().delete(REDISKEY, "articles");
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    /**
     * 批量删除文章
     * @param articles
     * @return
     */
    public boolean delArticles(String[] articles) {
        try {
            List<String> list = Arrays.asList(articles);
            list.forEach(aid -> {
                articleDao.delete(articleDao.findById(Integer.parseInt(aid)).get());
            });
            redisTemplate.opsForHash().delete(REDISKEY, "articles");
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    /**
     * 删除标签
     * @param tid
     * @return
     */
    public boolean delTag(Integer tid) {
        try {
            tagsDao.delete(tagsDao.findById(tid).get());
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    /**
     * 批量删除标签
     * @param tags
     * @return
     */
    public boolean delTags(String[] tags) {
        try {
            List<String> list = Arrays.asList(tags);
            list.forEach(tid -> {
                tagsDao.delete(tagsDao.findById(Integer.parseInt(tid)).get());
            });
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    /**
     * 添加标签
     * @param tag
     * @return
     */
    public boolean addTag(String tag) {
        if ("".equals(tag) || tag == null) return false;
        try {
            tagsDao.addTag(tag);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    public List<Tags> getTags() {
        return tagsDao.findAll();
    }
}
