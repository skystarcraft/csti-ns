package com.cstins.search.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: csti-ns
 * @description: 文章实体类
 * @author: 杨云龙
 **/

@Document(indexName = "art", type = "article")
public class Article implements Serializable {

    @Id
    private Integer article_id;                 //文章id

    private String article_title;               //文章标题

    private String article_context;             //文章内容

    private Integer article_view;               //被查看次数

    private Integer uid;                        //作者id

    private Date article_date;                  //发布时间

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_context() {
        return article_context;
    }

    public void setArticle_context(String article_context) {
        this.article_context = article_context;
    }

    public Integer getArticle_view() {
        return article_view;
    }

    public void setArticle_view(Integer article_view) {
        this.article_view = article_view;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getArticle_date() {
        return article_date;
    }

    public void setArticle_date(Date article_date) {
        this.article_date = article_date;
    }

    public Article() {
    }
}
