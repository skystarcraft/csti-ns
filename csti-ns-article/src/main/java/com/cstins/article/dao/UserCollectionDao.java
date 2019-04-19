package com.cstins.article.dao;

import com.cstins.article.entity.User_collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCollectionDao extends JpaRepository<User_collection, Integer> {

    List<User_collection> findAllByUidEquals(Integer uid);
}
