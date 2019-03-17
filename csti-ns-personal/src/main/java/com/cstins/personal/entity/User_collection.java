package com.cstins.personal.entity;

import javax.persistence.*;

/**
 * @program: csti-ns
 * @description: 用户收藏文章
 * @author: 杨云龙
 **/

@Entity
@Table(name = "user_collection")
public class User_collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;         //无实际意义

    @Column
    private Integer uid;        //用户id

    @Column
    private Integer aid;        //文章id

    public User_collection() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }
}
