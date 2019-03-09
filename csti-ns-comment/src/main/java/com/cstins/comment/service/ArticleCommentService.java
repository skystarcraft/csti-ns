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
        if (articleComment == null) return false;
        int result = articleCommentDao.addComment(articleComment.getAid(), articleComment.getUid(),
                articleComment.getArticle_context(), articleComment.getCdate());
        if (result > 0) {
            redisTemplate.opsForHash().delete(REDISKEY, "commentsByAid");
            redisTemplate.opsForHash().delete(REDISKEY, "commentsByUid");
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
        Object comments = redisTemplate.opsForHash().get(REDISKEY, "commentsByAid");
        if (comments != null && !"".equals(comments) && !"null".equals(comments)) {
            list = new ArrayList<>();
            JSONArray jsonArray = JSONArray.fromObject(comments);
            for (int i = 0; i < jsonArray.size(); i++) {
                ArticleComment articleComment = (ArticleComment) JSONObject.toBean((JSONObject) jsonArray.get(i)
                        , ArticleComment.class);
                list.add(articleComment);
            }
        } else {
            list = articleCommentDao.getAllByAidEquals(aid);
            JSONArray jsonArray = JSONArray.fromObject(list);
            redisTemplate.opsForHash().put(REDISKEY, "commentsByAid", jsonArray.toString());
        }
        return list;
    }

    public List<ArticleComment> getArticleCommentsByUid(Integer uid) {
        List<ArticleComment> list = null;
        Object comments = redisTemplate.opsForHash().get(REDISKEY, "commentsByUid");
        if (comments != null && !"".equals(comments) && !"null".equals(comments)) {
            list = new ArrayList<>();
            JSONArray jsonArray = JSONArray.fromObject(comments);
            for (int i = 0; i < jsonArray.size(); i++) {
                ArticleComment articleComment = (ArticleComment) JSONObject.toBean((JSONObject) jsonArray.get(i)
                        , ArticleComment.class);
                list.add(articleComment);
            }
        } else {
            list = articleCommentDao.getAllByUidEquals(uid);
            JSONArray jsonArray = JSONArray.fromObject(list);
            redisTemplate.opsForHash().put(REDISKEY, "commentsByUid", jsonArray.toString());
        }
        return list;
    }
}
