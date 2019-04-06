package com.cstins.res.dao;

import com.cstins.res.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByRidEqualsOrderByCtimeDesc(Integer rid);

    @Modifying
    @Transactional
    @Query(value = "insert into resource_comment (rid, uid, comment_context, ctime) values (?1, ?2, ?3, ?4)", nativeQuery = true)
    int addComment(Integer rid, Integer uid, String context, Date ctime);

}
