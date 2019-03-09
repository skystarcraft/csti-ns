package com.cstins.res.service;

import com.cstins.res.dao.CommentDao;
import com.cstins.res.entity.Comment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: csti-ns
 * @description: 评论相关服务
 * @author: 杨云龙
 **/

@Service
@Transactional
public class CommentService {

    private static final String REDISKEY = "csti:res";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CommentDao commentDao;

    public boolean addCopmment(Comment comment) {
        if (comment == null) return false;
        try {
            commentDao.addComment(comment.getRid(), comment.getUid(), comment.getComment_context(), comment.getCtime());
            redisTemplate.opsForHash().delete(REDISKEY, "resComment");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delComment(Comment comment) {
        if (comment == null) return false;
        try {
            commentDao.delete(comment);
            redisTemplate.opsForHash().delete(REDISKEY, "resComment");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Comment> getAllCommentByRid (Integer rid) {
        List<Comment> list = null;
        Object resComment = redisTemplate.opsForHash().get(REDISKEY, "resComment");
        if (resComment != null && !"".equals(resComment) && !"null".equals(resComment)) {
            list = new ArrayList<>();
            JSONArray jsonArray = JSONArray.fromObject(resComment);
            for (int i = 0; i < jsonArray.size(); i++) {
                Comment comment = (Comment) JSONObject.toBean((JSONObject) jsonArray.get(i)
                        , Comment.class);
                list.add(comment);
            }
        } else {
            list = commentDao.findAllByRidEquals(rid);
            JSONArray jsonArray = JSONArray.fromObject(list);
            redisTemplate.opsForHash().put(REDISKEY, "resComment", jsonArray.toString());
        }
        return list;
    }

}
