package com.cstins.comment.api;

import com.alibaba.fastjson.JSONObject;
import com.cstins.comment.entity.ArticleComment;
import com.cstins.comment.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public JSONObject getCommentsByAid(@PathVariable("aid") Integer aid) {
        List<ArticleComment> articleCommentsByAid = service.getArticleCommentsByAid(aid);
        JSONObject jsonObject = new JSONObject();
        if (articleCommentsByAid == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取列表失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取列表成功！");
            jsonObject.put("data", articleCommentsByAid);
        }
        return jsonObject;
    }

    @GetMapping("/user/comment/{uid}")
    public JSONObject getCommentsByUid(@PathVariable("uid") Integer uid) {
        List<ArticleComment> comments = service.getArticleCommentsByUid(uid);
        JSONObject jsonObject = new JSONObject();
        if (comments == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取列表失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取列表成功！");
            jsonObject.put("data", comments);
        }
        return jsonObject;
    }

    @PostMapping("/comment")
    public JSONObject addComment(@RequestBody ArticleComment articleComment) {
        if (articleComment == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 400);
            jsonObject.put("msg", "添加失败！");
            jsonObject.put("data", "");
            return jsonObject;
        }
        boolean b = service.addComment(articleComment);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "添加成功！");
            jsonObject.put("data", articleComment);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "添加失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @DeleteMapping("/comment")
    public JSONObject delComment(@RequestBody ArticleComment articleComment) {
        boolean b = service.delComment(articleComment);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", articleComment);
        }
        return jsonObject;
    }
}
