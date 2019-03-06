package com.cstins.article.api;

import com.cstins.article.entity.Tags;
import com.cstins.article.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: csti-ns
 * @description: 标签对外接口
 * @author: 杨云龙
 **/

@RestController
public class TagAPI {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public List<Tags> getTag() {
        return tagService.getAllTags();
    }

    @GetMapping("/tag/{tid}")
    public Tags getTag(@PathVariable("tid") Integer tid) {
        return tagService.getTag(tid);
    }

    @PostMapping("/tag")
    public boolean addTag(@RequestBody Tags tags){
        return tagService.addTag(tags);
    }

    @DeleteMapping("/tag")
    public boolean delTag(@RequestBody Tags tags){
        if (tags == null) return false;
        return tagService.delTag(tags);
    }
}
