package com.cstins.personal.dao;


import com.cstins.personal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findAllByTypeEquals(Integer type);

}
