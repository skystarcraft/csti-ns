package com.cstins.article.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: csti-ns
 * @description: 标签类
 * @author: 杨云龙
 **/

@Entity
@Table(name = "tags")
public class Tags implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tag_id;             //标签id

    @Column
    private String tag_name;            //标签内容

    public Tags() {
    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }
}
