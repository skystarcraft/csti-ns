package com.cstins.sso.dao;


import com.cstins.sso.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUidEquals(Integer uid);

    boolean existsByUidEquals(Integer uid);

}
