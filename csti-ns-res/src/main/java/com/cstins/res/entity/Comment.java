package com.cstins.res.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: csti-ns
 * @description: 资源评论实体类
 * @author: 杨云龙
 **/

@Entity
@Table(name = "resource_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer rid;

    @Column
    private Integer uid;

    @Column
    private String comment_context;

    @Column
    private Date ctime;

    public Comment() {
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getComment_context() {
        return comment_context;
    }

    public void setComment_context(String comment_context) {
        this.comment_context = comment_context;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
