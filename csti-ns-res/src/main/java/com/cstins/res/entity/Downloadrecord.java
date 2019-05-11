package com.cstins.res.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@Entity
@Table(name = "downloadrecord")
public class Downloadrecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer uid;

    @Column
    private Integer rid;

    @Column
    private Date downdate;

    @Column
    private String rname;

    public Downloadrecord() {
    }

    public Downloadrecord(Integer uid, Integer rid, Date downdate, String rname) {
        this.uid = uid;
        this.rid = rid;
        this.downdate = downdate;
        this.rname = rname;
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

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Date getDowndate() {
        return downdate;
    }

    public void setDowndate(Date downdate) {
        this.downdate = downdate;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
