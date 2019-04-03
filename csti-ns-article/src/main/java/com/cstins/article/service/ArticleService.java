package com.cstins.article.service;

import com.cstins.article.dao.ArticleDao;
import com.cstins.article.dao.ArticleTagDao;
import com.cstins.article.entity.Article;
import com.cstins.article.tools.JsonDateValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: csti-ns
 * @description: 文章相关服务
 * @author: 杨云龙
 **/

@Service
public class ArticleService {

    private final static String REDISKEY = "csti:article";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleTagDao articleTagDao;

    public List<Article> getAllArticles() {
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

    public Article getArticle(Integer id) {
        articleDao.addArticleView(id);
        Object article = redisTemplate.opsForHash().get(REDISKEY, id + "");
        if (article != null && !"".equals(article) && !"null".equals(article)) {
            JSONObject jsonObject = JSONObject.fromObject(article);
            Article a = (Article) JSONObject.toBean(jsonObject, Article.class);
            return a;
        } else {
            try {
                Article article1 = articleDao.findById(id).get();
                new JsonConfig().registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
                JSONObject jsonObject = JSONObject.fromObject(article1);
                redisTemplate.opsForHash().put(REDISKEY, id + "", jsonObject.toString());
                return article1;
            } catch (Exception e) {
                Article article1 = new Article("大胸弟你迷路了", "文章不存在", 0, 0, new java.util.Date());
                new JsonConfig().registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
                JSONObject jsonObject = JSONObject.fromObject(article1);
                redisTemplate.opsForHash().put(REDISKEY, id + "", jsonObject.toString());
            }
            return null;
        }
    }

    public List<Article> getArticlesByUid(Integer uid) {
        List<Article> articles = null;
        try {
            articles = articleDao.findAllByUidEquals(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }

    public boolean addOrUpdateArticle(Article article) {
        if (article == null) return false;
        articleDao.save(article);
        redisTemplate.opsForHash().delete(REDISKEY, article.getArticle_id() + "");
        new JsonConfig().registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
        JSONObject jsonObject = JSONObject.fromObject(article);
        redisTemplate.opsForHash().put(REDISKEY, article.getArticle_id() + "", jsonObject.toString());
        redisTemplate.opsForHash().delete(REDISKEY, "articles");
        return true;
    }

    public boolean delArticle(Article article) {
        if (article == null) return false;
        try {
            articleDao.delete(article);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delOneUserArticle(Integer user_id) {
        int result = articleDao.deleteAllByUidEquals(user_id);
        if (result > 0) {
            return true;
        }
        return false;
    }

    public boolean delArticles(String[] aids) {
        List<String> list = Arrays.asList(aids);
        try {
            list.forEach(article -> {
                articleDao.delete(articleDao.findById(Integer.parseInt(article)).get());
            });
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean addTag(Integer article, Integer tags) {
        if (article == null || tags == null) return false;
        try {
            articleTagDao.addTagToArticle(article, tags);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delTag(Integer article, Integer tags) {
        if (article == null || tags == null) return false;
        try {
            articleTagDao.delTagToArticle(article, tags);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
