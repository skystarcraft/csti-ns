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
    private Integer rmb;       //人民币

    @Column
    private Integer radd;       //积分

    public Integral_price() {
    }

    public Integer getIntegral_id() {
        return integral_id;
    }

    public void setIntegral_id(Integer integral_id) {
        this.integral_id = integral_id;
    }

    public Integer getRmb() {
        return rmb;
    }

    public void setRmb(Integer rmb) {
        this.rmb = rmb;
    }

    public Integer getRadd() {
        return radd;
    }

    public void setRadd(Integer radd) {
        this.radd = radd;
    }
}
