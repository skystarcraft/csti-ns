package com.cstins.front.dao;

import com.cstins.front.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface LinkDao extends JpaRepository<Link, Integer> {


    @Transactional
    @Modifying
    @Query(value = "insert into link (link_name, link_addr) values (?1, ?2)", nativeQuery = true)
    int addLink(String link_name, String link_addr);

    @Transactional
    @Modifying
    @Query(value = "update link set link_name = ?1, link_addr = ?2 where id = ?3", nativeQuery = true)
    int updateLink(String link_name, String link_addr, int id);

//    @Query(value = "select id,link_name,link_addr,link_type from Link where link_type = 0")
    List<Link> findByTypeEquals(Integer type);
}
