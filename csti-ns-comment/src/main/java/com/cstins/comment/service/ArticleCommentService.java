package com.cstins.comment.service;

import com.cstins.comment.dao.ArticleCommentDao;
import com.cstins.comment.entity.ArticleComment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: csti-ns
 * @description: 文章评论相关服务
 * @author: 杨云龙
 **/

@Service
@Transactional
public class ArticleCommentService {

    private static final String REDISKEY = "csti:comment";

    @Autowired
    private ArticleCommentDao articleCommentDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean addComment(ArticleComment articleComment) {
        if (articleComment == null || articleComment.getUid() == null || "".equals(articleComment.getUid())) return false;
        int result = articleCommentDao.addComment(articleComment.getAid(), articleComment.getUid(),
                articleComment.getArticle_context(), new Date());
        if (result > 0) {
            redisTemplate.opsForHash().delete(REDISKEY, "commentsByAid" + articleComment.getAid());
            redisTemplate.opsForHash().delete(REDISKEY, "commentsByUid" + articleComment.getAid());
            return true;
        }
        return false;
    }

    public boolean delComment (ArticleComment articleComment) {
        if (articleComment == null) return false;
        try {
            articleCommentDao.delete(articleComment);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<ArticleComment> getArticleCommentsByAid(Integer aid) {
        List<ArticleComment> list = null;
        Object comments = redisTemplate.opsForHash().get(REDISKEY, "commentsByAid" + aid);
        if (comments != null && !"".equals(comments) && !"null".equals(comments)) {
            list = new ArrayList<>();
            JSONArray jsonArray = JSONArray.fromObject(comments);
            for (int i = 0; i < jsonArray.size(); i++) {
                ArticleComment articleComment = (ArticleComment) JSONObject.toBean((JSONObject) jsonArray.get(i)
                        , ArticleComment.class);
                list.add(articleComment);
            }
        } else {
            list = articleCommentDao.findAllByAidEqualsOrderByCdateDesc(aid);
            JSONArray jsonArray = JSONArray.fromObject(list);
            redisTemplate.opsForHash().put(REDISKEY, "commentsByAid" + aid, jsonArray.toString());
        }
        return list;
    }

    public List<ArticleComment> getArticleCommentsByUid(Integer uid) {
        List<ArticleComment> list = null;
        Object comments = redisTemplate.opsForHash().get(REDISKEY, "commentsByUid" + uid);
        if (comments != null && !"".equals(comments) && !"null".equals(comments)) {
            list = new ArrayList<>();
            JSONArray jsonArray = JSONArray.fromObject(comments);
            for (int i = 0; i < jsonArray.size(); i++) {
                ArticleComment articleComment = (ArticleComment) JSONObject.toBean((JSONObject) jsonArray.get(i)
                        , ArticleComment.class);
                list.add(articleComment);
            }
        } else {
            list = articleCommentDao.findAllByUidEquals(uid);
            JSONArray jsonArray = JSONArray.fromObject(list);
            redisTemplate.opsForHash().put(REDISKEY, "commentsByUid" + uid, jsonArray.toString());
        }
        return list;
    }
}
