package com.cstins.article.service;

import com.cstins.article.dao.TagsDao;
import com.cstins.article.entity.Tags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(TagService.class);

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

    /**
     * 批量删除标签
     * @param list
     * @return
     */
    public boolean delTags(List<Integer> list) {
        try {
            list.forEach(tid -> {
                tagsDao.delete(tagsDao.findById(tid).get());
            });
        } catch (Exception e) {
            logger.error(e);
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
