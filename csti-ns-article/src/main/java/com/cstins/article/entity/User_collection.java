package com.cstins.article.entity;

import javax.persistence.*;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@Entity
@Table(name = "user_collection")
public class User_collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer uid;

    @Column
    private Integer aid;

    public User_collection() {
    }

    public User_collection(Integer uid, Integer aid) {
        this.uid = uid;
        this.aid = aid;
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
