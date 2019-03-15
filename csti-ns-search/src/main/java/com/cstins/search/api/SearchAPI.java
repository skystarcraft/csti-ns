package com.cstins.search.api;

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
@RequestMapping("/search")
public class SearchAPI {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleService service;

    /**
     * 查 id
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Article searchArticle(@PathVariable("id") Integer id) {
        return articleDao.findById(id).get();
    }

    /**
     * 全文检索
     * @return
     */
    @GetMapping("/select/{text}")
    public List<Article> searchArticles(@PathVariable("text") String text) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(text);
        Iterable<Article> search = articleDao.search(builder);
        List<Article> list = new ArrayList<>();
        search.forEach(article -> {
            list.add(article);
        });
        return list;
    }

    @PostMapping("/insert")
    public Article insertArticle(@RequestBody Article article) {
        articleDao.save(article);
        return article;
    }

    @PostMapping("/inserts")
    public boolean insertAllArticles() {
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
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @DeleteMapping("/del/{id}")
    public Article delArticle(@PathVariable("id") Integer id) {
        Article article = articleDao.findById(id).get();
        articleDao.delete(article);
        return article;
    }
}
