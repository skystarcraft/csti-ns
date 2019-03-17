package com.cstins.xpay.entity;

import javax.persistence.*;

/**
 * @program: csti-ns
 * @description: 人民币对应积分表
 * @author: 杨云龙
 **/

@Entity
@Table(name = "integral_price")
public class Integral_price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer integral_id;

    @Column
    private Integer integral_rmb;       //人民币

    @Column
    private Integer integral_add;       //积分

    public Integral_price() {
    }

    public Integer getIntegral_id() {
        return integral_id;
    }

    public void setIntegral_id(Integer integral_id) {
        this.integral_id = integral_id;
    }

    public Integer getIntegral_rmb() {
        return integral_rmb;
    }

    public void setIntegral_rmb(Integer integral_rmb) {
        this.integral_rmb = integral_rmb;
    }

    public Integer getIntegral_add() {
        return integral_add;
    }

    public void setIntegral_add(Integer integral_add) {
        this.integral_add = integral_add;
    }
}
