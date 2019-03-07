package com.cstins.comment.api;

import com.cstins.comment.entity.ArticleComment;
import com.cstins.comment.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: csti-ns
 * @description: 评论相关API
 * @author: 杨云龙
 **/

@RestController
public class CommentAPI {

    @Autowired
    private ArticleCommentService service;


    @GetMapping("/comment/{aid}")
    public List<ArticleComment> getCommentsByAid(@PathVariable("aid") Integer aid) {
        return service.getArticleCommentsByAid(aid);
    }

    @GetMapping("/user/comment/{uid}")
    public List<ArticleComment> getCommentsByUid(@PathVariable("uid") Integer uid) {
        return service.getArticleCommentsByUid(uid);
    }

    @PostMapping("/comment")
    public boolean addComment(@RequestBody ArticleComment articleComment) {
        if (articleComment == null) return false;
        return service.addComment(articleComment);
    }

    @DeleteMapping("/comment")
    public boolean delComment(@RequestBody ArticleComment articleComment) {
        if (articleComment == null) return false;
        return service.delComment(articleComment);
    }
}
