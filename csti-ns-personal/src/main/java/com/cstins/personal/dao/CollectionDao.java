package com.cstins.personal.dao;

import com.cstins.personal.entity.User_collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CollectionDao extends JpaRepository<User_collection, Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into user_collection (uid,aid) values (?1, ?2)", nativeQuery = true)
    int collectionArticle(Integer uid, Integer aid);

    @Modifying
    @Transactional
    @Query(value = "delete from user_collection where uid = ?1 and aid = ?2", nativeQuery = true)
    int cancelCollection(Integer uid, Integer aid);


    List<User_collection> findAllByUidEquals(Integer uid);
}
