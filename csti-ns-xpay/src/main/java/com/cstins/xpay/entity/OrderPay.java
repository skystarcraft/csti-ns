package com.cstins.xpay.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: csti-ns
 * @description: 订单表实体类
 * @author: 杨云龙
 **/

@Entity
@Table(name = "orderpay")
public class OrderPay {

    @Id
    private Integer order_id;           //订单id

    @Column
    private Integer uid;                //用户id

    @Column
    private Date createtime;            //创建时间

    @Column
    private Integer imoney;             //充值金钱

    public OrderPay() {
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public OrderPay(Integer order_id, Integer uid, Date ctime, Integer imoney) {
        this.order_id = order_id;
        this.uid = uid;
        this.createtime = ctime;
        this.imoney = imoney;
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
