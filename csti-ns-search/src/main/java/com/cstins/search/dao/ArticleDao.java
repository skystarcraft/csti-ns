package com.cstins.search.dao;

import com.cstins.search.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleDao extends ElasticsearchRepository<Article, Integer> {

}
