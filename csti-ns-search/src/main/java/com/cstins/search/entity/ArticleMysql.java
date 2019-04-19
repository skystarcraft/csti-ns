package com.cstins.search.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: csti-ns
 * @description: 文章实体类
 * @author: 杨云龙
 **/

@Entity
@Table(name = "article")
public class ArticleMysql implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;                 //文章id

    @Column
    private String article_title;               //文章标题

    @Column
    private String article_context;             //文章内容

    @Column
    private Integer article_view;               //被查看次数

    @Column
    private Integer uid;                    //作者id

    @Column
    private Date adate;                  //发布时间

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER) //  级联保存 更新 删除 刷新 延迟加载
//    @JoinColumn(name = "article_id")
//    private Set<Tags> tags = new HashSet<>();   //外键关联


    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
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

    public Date getAdate() {
        return adate;
    }

    public void setAdate(Date adate) {
        this.adate = adate;
    }

    public ArticleMysql() {
    }
}
