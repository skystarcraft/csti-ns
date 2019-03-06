package com.cstins.article.dao;

import com.cstins.article.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TagsDao extends JpaRepository<Tags, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into tags (tag_name) values (?1)", nativeQuery = true)
    int addTag(String tag_name);
}
