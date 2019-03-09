package com.cstins.res.entity;

import javax.persistence.*;

/**
 * @program: csti-ns
 * @description: 资源实体类
 * @author: 杨云龙
 **/

@Entity
@Table(name = "resource")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;            //资源id

    @Column
    private String rname;           //资源名称

    @Column
    private Integer rscore;         //资源所耗积分

    @Column
    private Integer rdownload;      //资源下载次数

    @Column
    private Double rstar;           //资源星级

    @Column
    private Integer ruid;           //上传用户id

    @Column
    private String raddr;           //资源地址

    @Column
    private String payaddr;         //交易二维码地址

    public Resource() {
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getRscore() {
        return rscore;
    }

    public void setRscore(Integer rscore) {
        this.rscore = rscore;
    }

    public Integer getRdownload() {
        return rdownload;
    }

    public void setRdownload(Integer rdownload) {
        this.rdownload = rdownload;
    }

    public Double getRstar() {
        return rstar;
    }

    public void setRstar(Double rstar) {
        this.rstar = rstar;
    }

    public Integer getRuid() {
        return ruid;
    }

    public void setRuid(Integer ruid) {
        this.ruid = ruid;
    }

    public String getRaddr() {
        return raddr;
    }

    public void setRaddr(String raddr) {
        this.raddr = raddr;
    }

    public String getPayaddr() {
        return payaddr;
    }

    public void setPayaddr(String payaddr) {
        this.payaddr = payaddr;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
