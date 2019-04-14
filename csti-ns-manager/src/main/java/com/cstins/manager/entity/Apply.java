package com.cstins.manager.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@Entity
@Table(name = "apply")
public class Apply implements Serializable {

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
