package com.cstins.search.api;

import com.alibaba.fastjson.JSONObject;
import com.cstins.search.dao.ArticleDao;
import com.cstins.search.entity.Article;
import com.cstins.search.entity.ArticleMysql;
import com.cstins.search.service.ArticleService;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: csti-ns
 * @description: 搜索模块相关API
 * @author: 杨云龙
 **/
@RestController
public class SearchAPI {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleService service;

    /**
     * 写这个没用的方法是因为vue设置代理要必须能访问
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "";
    }

    /**
     * 查 id
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public JSONObject searchArticle(@PathVariable("id") Integer id) {
        Article article = articleDao.findById(id).get();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "获取成功！");
        jsonObject.put("data", article);
        return jsonObject;
    }

    /**
     * 全文检索
     * @return
     */
    @GetMapping("/select/{text}")
    public JSONObject searchArticles(@PathVariable("text") String text) {
        JSONObject jsonObject = new JSONObject();
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(text);
        Iterable<Article> search = articleDao.search(builder);
        List<Article> list = new ArrayList<>();
        search.forEach(article -> {
            list.add(article);
        });
        jsonObject.put("code", 200);
        jsonObject.put("msg", "获取成功！");
        jsonObject.put("data", list);
        return jsonObject;
    }

    @PostMapping("/addes")
    public JSONObject insertArticle(@RequestBody Article article) {
        JSONObject jsonObject = new JSONObject();
        try {
            Article save = articleDao.save(article);
            jsonObject.put("code", 200);
            jsonObject.put("msg", "插入成功！");
            jsonObject.put("data", save);
        } catch (Exception e) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "插入失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @PostMapping("/inserts")
    public JSONObject insertAllArticles() {
        JSONObject jsonObject = new JSONObject();
        try {
            List<ArticleMysql> articles = service.getAll();
            for (int i = 0; i < articles.size(); i++) {
                ArticleMysql articleMysql = articles.get(i);
                Article article = new Article();
                article.setArticle_id(articleMysql.getArticle_id());
                article.setArticle_title(articleMysql.getArticle_title());
                article.setArticle_context(articleMysql.getArticle_context());
                article.setArticle_view(articleMysql.getArticle_view());
                article.setUid(articleMysql.getUid());
                article.setArticle_date(articleMysql.getArticle_date());
                articleDao.save(article);
            }
            jsonObject.put("code", 200);
            jsonObject.put("msg", "插入成功！");
            jsonObject.put("data", "");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", 400);
            jsonObject.put("msg", "插入失败！");
            jsonObject.put("data", e.getMessage());
        }
        return jsonObject;
    }

    @DeleteMapping("/del/{id}")
    public JSONObject delArticle(@PathVariable("id") Integer id) {
        Article article = articleDao.findById(id).get();
        articleDao.delete(article);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "删除成功！");
        jsonObject.put("data", "");
        return jsonObject;
    }
}
