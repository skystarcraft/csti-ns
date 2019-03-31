package com.cstins.article.api;

import com.alibaba.fastjson.JSONObject;
import com.cstins.article.entity.Article;
import com.cstins.article.entity.Tags;
import com.cstins.article.service.ArticleService;
import com.cstins.article.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: csti-ns
 * @description: 文章对外开放的API
 * @author: 杨云龙
 **/

@RestController
public class ArticleAPI {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @GetMapping("/articles")
    public JSONObject getAllArticle() {
        List<Article> articles = articleService.getAllArticles();
        JSONObject jsonObject = new JSONObject();
        if (articles == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取列表失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取列表成功！");
            jsonObject.put("data", articles);
        }
        return jsonObject;
    }

    @GetMapping("/article/{aid}")
    public JSONObject getArticle(@PathVariable("aid") Integer aid) {
        Article article = articleService.getArticle(aid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "获取成功！");
        jsonObject.put("data", article);
        return jsonObject;
    }

    @PutMapping("/article")
    public JSONObject updateArticle(@RequestBody Article article) {
        boolean b = articleService.addOrUpdateArticle(article);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "更新成功！");
            jsonObject.put("data", article);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "更新失败！");
            jsonObject.put("data", article);
        }
        return jsonObject;
    }

    @PostMapping("/article")
    public JSONObject addArticle(@RequestBody Article article) {
        article.setArticle_view(0);
        boolean b = articleService.addOrUpdateArticle(article);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "添加成功！");
            jsonObject.put("data", article);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "添加失败！");
            jsonObject.put("data", article);
        }
        return jsonObject;
    }

    @DeleteMapping("/article")
    public JSONObject delArticle(@RequestBody Article article) {
        boolean b = articleService.delArticle(article);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", article);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", article);
        }
        return jsonObject;
    }

    @DeleteMapping("/article/{aid}")
    public JSONObject delAllArticle(@PathVariable("aid") String[] aids) {
        boolean b = articleService.delArticles(aids);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @PostMapping("/art/{aid}/tag/{tid}")
    public JSONObject addTagtoArticle(@PathVariable("aid") Integer aid, @PathVariable("tid") Integer tid) {
        boolean b = articleService.addTag(aid, tid);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            Tags tag = tagService.getTag(tid);
            jsonObject.put("code", 200);
            jsonObject.put("msg", "添加成功！");
            jsonObject.put("data", tag);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "添加失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @DeleteMapping("/art/{aid}/tag/{tid}")
    public JSONObject delTagtoArticle(@PathVariable("aid") Integer aid, @PathVariable("tid") Integer tid) {
        boolean b = articleService.delTag(aid, tid);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            Tags tag = tagService.getTag(tid);
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", tag);
        }
        return jsonObject;
    }
}
