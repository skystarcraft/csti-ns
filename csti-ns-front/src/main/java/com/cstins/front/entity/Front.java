package com.cstins.front.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: csti-ns
 * @description: 前台实体类
 * @author: 杨云龙
 **/

@Entity
@Table(name = "front")
public class Front implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String introduction;

    @Column
    private String group_code;

    @Column
    private String gzh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public String getGzh() {
        return gzh;
    }

    public void setGzh(String gzh) {
        this.gzh = gzh;
    }

    public Front() {
    }
}
