package com.cstins.manager.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: csti-ns
 * @description: 友情链接实体类
 * @author: 杨云龙
 **/

@Entity
@Table(name = "link")
public class Link implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String link_name;

    @Column
    private String link_addr;

    @Column
    private Integer type;

    public Link() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink_name() {
        return link_name;
    }

    public void setLink_name(String link_name) {
        this.link_name = link_name;
    }

    public String getLink_addr() {
        return link_addr;
    }

    public void setLink_addr(String link_addr) {
        this.link_addr = link_addr;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
