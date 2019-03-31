package com.cstins.front.api;

import com.alibaba.fastjson.JSONObject;
import com.cstins.front.entity.Link;
import com.cstins.front.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: csti-ns
 * @description: 友情链接相关API
 * @author: 杨云龙
 **/

@RestController
public class LinkAPI {

    @Autowired
    private LinkService linkService;

    @GetMapping("/links")
    public JSONObject getLinks() {
        List<Link> links = linkService.getLinks();
        JSONObject jsonObject = new JSONObject();
        if (links == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取列表失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取列表成功！");
            jsonObject.put("data", links);
        }
        return jsonObject;
    }

    @PostMapping("/link")
    public JSONObject addLink(@RequestBody Link link) {
        boolean b = linkService.addLink(link);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "添加成功！");
            jsonObject.put("data", link);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "添加失败！");
            jsonObject.put("data", link);
        }
        return jsonObject;
    }

    @PutMapping("/link")
    public JSONObject updateLink(@RequestBody Link link) {
        boolean b = linkService.updateLink(link);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "更新成功！");
            jsonObject.put("data", link);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "更新失败！");
            jsonObject.put("data", link);
        }
        return jsonObject;
    }

    /**
     * @param link
     * @return
     */
    @DeleteMapping("/link")
    public JSONObject delLink(@RequestBody Link link) {
        boolean b = linkService.delLnk(link);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", link);
        }
        return jsonObject;
    }
}
