package com.cstins.xpay.dao;


import com.cstins.xpay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserDao extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update user set uintegral = uintegral + ?1 where uid = ?2", nativeQuery = true)
    int addScore(Integer uintegral, Integer uid);

}
