package com.cstins.search.dao;

import com.cstins.search.entity.ArticleMysql;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysqlDao extends JpaRepository<ArticleMysql, Integer> {
}
