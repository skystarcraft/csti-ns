package com.cstins.article.dao;

import com.cstins.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ArticleDao extends JpaRepository<Article, Integer> {

    int deleteAllByUidEquals(Integer user_id);

    @Modifying
    @Transactional
    @Query(value = "update article set article_view = article_view + 1 where aid = ?1", nativeQuery = true)
    int addArticleView(Integer aid);

    List<Article> findAllByUidEquals(Integer uid);

    List<Article> findAllByOrderByAdateDesc();

    List<Article> findAllByAidIsIn(List<Integer> list);
}
