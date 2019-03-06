package com.cstins.article.dao;

import com.cstins.article.entity.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ArticleTagDao extends JpaRepository<ArticleTag, Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into article_tag values (?1, ?2)", nativeQuery = true)
    int addTagToArticle(Integer aid, Integer tid);

    @Modifying
    @Transactional
    @Query(value = "delete from article_tag where article_id = ?1 and tag_id = ?2", nativeQuery = true)
    int delTagToArticle(Integer article, Integer tags);
}
