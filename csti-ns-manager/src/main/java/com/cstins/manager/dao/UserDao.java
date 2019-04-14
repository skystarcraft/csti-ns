package com.cstins.manager.dao;


import com.cstins.manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findAllByTypeEquals(Integer type);

    @Modifying
    @Transactional
    @Query(value = "update user set type = 2 where uid = ?1", nativeQuery = true)
    int changeType(Integer uid);

    User findByUidEquals(Integer uid);

    boolean existsByUidEquals(Integer uid);

    List<User> findAllByUidIn(List<Integer> list);
}
