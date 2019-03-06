package com.cstins.article.api;

import com.cstins.article.entity.Article;
import com.cstins.article.service.ArticleService;
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

    @GetMapping("/articles")
    public List<Article> getAllArticle() {
        return articleService.getAllArticles();
    }

    @GetMapping("/article/{aid}")
    public Article getArticle(@PathVariable("aid") Integer aid) {
        return articleService.getArticle(aid);
    }

    @PutMapping("/article")
    public boolean updateArticle(@RequestBody Article article) {
        return articleService.addOrUpdateArticle(article);
    }

    @PostMapping("/article")
    public boolean addArticle(@RequestBody Article article) {
        article.setArticle_view(0);
        return articleService.addOrUpdateArticle(article);
    }

    @DeleteMapping("/article")
    public boolean delArticle(@RequestBody Article article) {
        return articleService.delArticle(article);
    }

    @DeleteMapping("/article/{aid}")
    public boolean delAllArticle(@PathVariable("aid") String[] aids) {
        return articleService.delArticles(aids);
    }

    @PostMapping("/art/{aid}/tag/{tid}")
    public boolean addTagtoArticle(@PathVariable("aid") Integer aid, @PathVariable("tid") Integer tid) {
        return articleService.addTag(aid, tid);
    }

    @DeleteMapping("/art/{aid}/tag/{tid}")
    public boolean delTagtoArticle(@PathVariable("aid") Integer aid, @PathVariable("tid") Integer tid) {
        return articleService.delTag(aid, tid);
    }
}
