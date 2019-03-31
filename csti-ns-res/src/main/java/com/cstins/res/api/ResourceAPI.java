package com.cstins.res.api;

import com.alibaba.fastjson.JSONObject;
import com.cstins.res.entity.Resource;
import com.cstins.res.service.ResourceService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @program: csti-ns
 * @description: 资源相关API
 * @author: 杨云龙
 **/

@Controller
public class ResourceAPI {

    @Autowired
    private ResourceService service;

    @GetMapping("/res/upload")
    public String getUpIndex() {
        return "file";
    }

    @ResponseBody
    @PostMapping("/res/uploadToFast")
    public JSONObject uploadRes (@RequestParam("fileName") MultipartFile file) {
        String fallback = null;
        JSONObject jsonObject = new JSONObject();
        try {
            String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
            if ("jpg".equals(suffix) || "jpeg".equals(suffix) || "png".equals(suffix)) {
                fallback = service.upfileImage(file.getInputStream(), file.getSize(), suffix);
            } else {
                fallback = service.uploadFile(file);
            }
            jsonObject.put("code", 200);
            jsonObject.put("msg", "上传成功！");
            jsonObject.put("data", "");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", 400);
            jsonObject.put("msg", "上传失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @ResponseBody
    @PostMapping("/res/resource")
    public JSONObject uploadRes2 (@RequestBody Resource resource) {
        boolean b = service.uploadRes(resource);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "上传成功！");
            jsonObject.put("data", resource);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "上传失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @ResponseBody
    @GetMapping("/res/{rid}")
    public JSONObject getRes(@PathVariable("rid") Integer rid) {
        Resource res = service.getRes(rid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "获取成功！");
        jsonObject.put("data", res);
        return jsonObject;
    }

    @ResponseBody
    @GetMapping("/res/download/{rid}/user/{uid}")
    public JSONObject downloadRes(@PathVariable("rid") Integer rid, @PathVariable("uid") Integer uid) {
        String path = service.downloadRes(rid, uid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "获取成功！");
        jsonObject.put("data", path);
        return jsonObject;
    }

    @ResponseBody
    @DeleteMapping("/res/{rid}")
    public JSONObject delRes (@PathVariable("rid") Integer rid) {
        boolean b = service.delRes(rid);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", rid);
        }
        return jsonObject;
    }

}
