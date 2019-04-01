package com.cstins.comment.dao;

import com.cstins.comment.entity.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ArticleCommentDao extends JpaRepository<ArticleComment, Integer> {

    /**
     * 查询文章id为aid的所有评论
     * @param aid
     * @return
     */
    List<ArticleComment> findAllByAidEquals(Integer aid);


    /**
     * 查询用户为uid的所有评论
     * @param uid
     * @return
     */
    List<ArticleComment> findAllByUidEquals(Integer uid);

    @Transactional
    @Modifying
    @Query(value = "insert into article_comment (aid, uid, article_context, cdate) values (?1, ?2, ?3, ?4)", nativeQuery = true)
    int addComment(Integer aid, Integer uid, String context, Date cdate);

}
