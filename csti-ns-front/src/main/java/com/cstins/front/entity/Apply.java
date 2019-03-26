package com.cstins.front.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: csti-ns
 * @description: 纳新实体类
 * @author: 杨云龙
 **/

@Entity
@Table(name = "apply")
public class Apply {

    @Id
    private Integer appid;

    public Apply() {
    }

    public Apply(Integer appid) {
        this.appid = appid;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }
}
