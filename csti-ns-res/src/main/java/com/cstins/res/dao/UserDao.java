package com.cstins.res.dao;


import com.cstins.res.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findAllByTypeEquals(Integer type);

    @Modifying
    @Transactional
    @Query(value = "update user set uintegral = uintegral - ?1 where uid = ?2", nativeQuery = true)
    int reduce_points(Integer score, Integer uid);
}
