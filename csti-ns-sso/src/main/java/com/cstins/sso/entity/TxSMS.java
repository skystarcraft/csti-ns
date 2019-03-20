package com.cstins.sso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: csti-ns
 * @description: 腾讯剩余条数
 * @author: 杨云龙
 **/
@Entity
@Table(name = "txsms")
public class TxSMS {

    @Id
    private Integer id;

    @Column
    private Integer count;

    public TxSMS() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public TxSMS(Integer id, Integer count) {
        this.id = id;
        this.count = count;
    }
}
