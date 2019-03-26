package com.cstins.res.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: csti-ns
 * @description: 用户实体类
 * @author: 杨云龙
 **/
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    private Integer uid;         //学号

    @Column
    private String user_name;       //姓名

    @Column
    private String user_password;   //密码

    @Column
    private Integer usex;           //用户性别

    @Column
    private String user_image;      //头像URL

    @Column
    private Integer uintegral;   //积分

    @Column
    private Integer type;           //用户类别0-普通用户，1-会员，2-组内成员,3-超级管理员

    @Column
    private Date user_birth;        //生日

    @Column
    private String user_address;    //入学前地址

    @Column
    private String user_class;      //班级，如计科二班

    @Column
    private Integer ugrate;      //年级，如2015,2016

    @Column
    private String user_payimage;   //收款二维码连接

    @Column
    private Integer user_pay;       //已充值金钱

    @Column
    private Long user_phone;        //手机号

    @Column
    private String user_mail;       //邮箱

    public User() {
    }

    public Integer getUsex() {
        return usex;
    }

    public void setUsex(Integer usex) {
        this.usex = usex;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUintegral() {
        return uintegral;
    }

    public void setUintegral(Integer uintegral) {
        this.uintegral = uintegral;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(Date user_birth) {
        this.user_birth = user_birth;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_class() {
        return user_class;
    }

    public void setUser_class(String user_class) {
        this.user_class = user_class;
    }

    public Integer getUgrate() {
        return ugrate;
    }

    public void setUgrate(Integer ugrate) {
        this.ugrate = ugrate;
    }

    public String getUser_payimage() {
        return user_payimage;
    }

    public void setUser_payimage(String user_payimage) {
        this.user_payimage = user_payimage;
    }

    public Integer getUser_pay() {
        return user_pay;
    }

    public void setUser_pay(Integer user_pay) {
        this.user_pay = user_pay;
    }

    public Long getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(Long user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }
}
