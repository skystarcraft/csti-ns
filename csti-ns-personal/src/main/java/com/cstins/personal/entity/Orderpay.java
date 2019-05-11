package com.cstins.personal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
//import java.util.Date;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/
@Entity
@Table(name = "orderpay")
public class Orderpay implements Serializable {

    @Id
    private Integer order_id;

    @Column
    private Integer uid;

    @Column
    private Date createtime;

    @Column
    private Integer imoney;

    public Orderpay() {
    }

    public Orderpay(Integer order_id, Integer uid, Date createtime, Integer imoney) {
        this.order_id = order_id;
        this.uid = uid;
        this.createtime = createtime;
        this.imoney = imoney;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getImoney() {
        return imoney;
    }

    public void setImoney(Integer imoney) {
        this.imoney = imoney;
    }
}
