package com.cstins.article.service;

import com.cstins.article.dao.TagsDao;
import com.cstins.article.entity.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: csti-ns
 * @description: 标签相关服务
 * @author: 杨云龙
 **/

@Service
public class TagService {

    @Autowired
    private TagsDao tagsDao;

    public boolean addTag(Tags tag) {
        if (tag == null) return false;
        int result = tagsDao.addTag(tag.getTag_name());
        if (result > 0 ) {
            return true;
        }
        return false;
    }

    public boolean delTag(Tags tag) {
        if (tag == null) return false;
        try {
            tagsDao.delete(tag);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Tags> getAllTags() {
        return tagsDao.findAll();
    }

    public Tags getTag(Integer tid) {
        return tagsDao.findById(tid).get();
    }

}
