package com.cstins.comment.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: csti-ns
 * @description: 文章评论实体类
 * @author: 杨云龙
 **/

@Entity
@Table(name = "article_comment")
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer acid;           //评论id

    @Column
    private Integer aid;            //文章id

    @Column
    private Integer uid;            //评论用户id

    @Column
    private String article_context; //评论内容

    @Column
    private Date cdate;             //评论时间

    public ArticleComment() {
    }

    public Integer getAcid() {
        return acid;
    }

    public void setAcid(Integer acid) {
        this.acid = acid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getArticle_context() {
        return article_context;
    }

    public void setArticle_context(String article_context) {
        this.article_context = article_context;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}
