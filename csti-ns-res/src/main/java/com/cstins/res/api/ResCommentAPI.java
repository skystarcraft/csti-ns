package com.cstins.res.api;

import com.cstins.res.entity.Comment;
import com.cstins.res.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: csti-ns
 * @description: 资源评论相关API
 * @author: 杨云龙
 **/

@RestController
public class ResCommentAPI {

    @Autowired
    private CommentService commentService;

    @PostMapping("/res/comment")
    public boolean addComment (@RequestBody Comment comment) {
        if (comment == null) return false;
        return commentService.addCopmment(comment);
    }

    @DeleteMapping("/res/comment")
    public boolean delComment (@RequestBody Comment comment) {
        if (comment == null) return false;
        return commentService.delComment(comment);
    }

    @GetMapping("/res/comments/{rid}")
    public List<Comment> getAllCommentsByRid (@PathVariable("rid") Integer rid) {
        return commentService.getAllCommentByRid(rid);
    }

}
