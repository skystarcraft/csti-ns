package com.cstins.manager.service;

import com.cstins.manager.dao.ApplyDao;
import com.cstins.manager.dao.ArticleDao;
import com.cstins.manager.dao.TagsDao;
import com.cstins.manager.dao.UserDao;
import com.cstins.manager.entity.Apply;
import com.cstins.manager.entity.Article;
import com.cstins.manager.entity.Tags;
import com.cstins.manager.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private ApplyDao applyDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String REDISKEY = "csti:ns";

    /**
     * 查询所有组内成员
     * @return
     */
    public List<User> getUsers() {
        List<User> users = null;
        try {
            users = userDao.findAllByTypeEquals(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getAllUser() {
        List<User> users = null;
        try {
            users = userDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
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
                User user2 = getUserById(Integer.parseInt(user));
                if (user2.getType() < 2) {
                    userDao.changeType(user2.getUid());
                }
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
            User user = getUserById(uid);
            if (user.getType() < 2) {
                userDao.changeType(uid);
                redisTemplate.opsForHash().delete(REDISKEY, "user");
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }

    public List<Article> getArticles() {
        List<Article> list = null;
        try {
            Object articles = redisTemplate.opsForHash().get(REDISKEY, "articles");
            if (articles != null && !"".equals(articles) && !"null".equals(articles)) {
                list = new ArrayList<>();
                JSONArray jsonArray = JSONArray.fromObject(articles);
                for (int i = 0; i < jsonArray.size(); i++) {
                    Article article = (Article) JSONObject.toBean((JSONObject) jsonArray.get(i), Article.class);
                    list.add(article);
                }
            } else {
                list = articleDao.findAll();
                JSONArray jsonArray = JSONArray.fromObject(list);
                redisTemplate.opsForHash().put(REDISKEY, "articles", jsonArray.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
        List<Tags> tags = null;
        try {
            tags = tagsDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tags;
    }

    public List<User> getApplyUser() {
        List<User> users = null;
        try {
            List<Apply> all = applyDao.findAll();
            List<Integer> applys = new ArrayList<>();
            all.forEach(apply -> {
                applys.add(apply.getAppid());
            });
            users = userDao.findAllByUidIn(applys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
