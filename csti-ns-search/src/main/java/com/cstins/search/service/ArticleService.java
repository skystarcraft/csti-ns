package com.cstins.search.service;

import com.cstins.search.dao.MysqlDao;
import com.cstins.search.entity.ArticleMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@Service
@Transactional
public class ArticleService {

    @Autowired
    private MysqlDao dao;

    public List<ArticleMysql> getAll() {
        return dao.findAll();
    }

}
