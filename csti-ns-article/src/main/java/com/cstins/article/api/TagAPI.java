package com.cstins.article.api;

import com.alibaba.fastjson.JSONObject;
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
    public JSONObject getTag() {
        List<Tags> tags = tagService.getAllTags();
        JSONObject jsonObject = new JSONObject();
        if (tags == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取列表失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取列表成功！");
            jsonObject.put("data", tags);
        }
        return jsonObject;
    }

    @GetMapping("/tag/{tid}")
    public JSONObject getTag(@PathVariable("tid") Integer tid) {
        Tags tag = tagService.getTag(tid);
        JSONObject jsonObject = new JSONObject();
        if (tag == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取成功！");
            jsonObject.put("data", tag);
        }
        return jsonObject;
    }

    @PostMapping("/tag")
    public JSONObject addTag(@RequestBody Tags tags){
        boolean b = tagService.addTag(tags);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "添加成功！");
            jsonObject.put("data", tags);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "添加失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @DeleteMapping("/tag")
    public JSONObject delTag(@RequestBody Tags tags){
        boolean b = tagService.delTag(tags);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", tags);
        }
        return jsonObject;
    }
}
