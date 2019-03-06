package com.cstins.article.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @program: csti-ns
 * @description: 关联类
 * @author: 杨云龙
 **/

@Entity
@Table(name = "article_tag")
public class ArticleTag implements Serializable {

    @Id
    private Integer article_id;

    @Column
    private Integer tag_id;

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    public ArticleTag() {
    }
}
