package com.cstins.sso.entity;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/
public class LoginUser {

    private Integer uid;

    private String user_password;

    public LoginUser() {
    }

    public LoginUser(Integer uid, String user_password) {
        this.uid = uid;
        this.user_password = user_password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
