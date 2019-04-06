package com.cstins.res.api;

import com.alibaba.fastjson.JSONObject;
import com.cstins.res.entity.Comment;
import com.cstins.res.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public JSONObject addComment (@RequestBody Comment comment) {
        comment.setCtime(new Date());
        boolean b = commentService.addCopmment(comment);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "评论成功！");
            jsonObject.put("data", comment);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "评论失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @DeleteMapping("/res/comment")
    public JSONObject delComment (@RequestBody Comment comment) {
        boolean b = commentService.delComment(comment);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", comment);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @GetMapping("/res/comments/{rid}")
    public JSONObject getAllCommentsByRid (@PathVariable("rid") Integer rid) {
        List<Comment> comment = commentService.getAllCommentByRid(rid);
        JSONObject jsonObject = new JSONObject();
        if (comment == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取列表失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取列表成功！");
            jsonObject.put("data", comment);
        }
        return jsonObject;
    }

}
