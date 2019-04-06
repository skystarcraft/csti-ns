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
import java.util.List;

/**
 * @program: csti-ns
 * @description: 资源相关API
 * @author: 杨云龙
 **/

@Controller
public class ResourceAPI {

    @Autowired
    private ResourceService service;

    @GetMapping("/upload")
    public String getUpIndex() {
        return "file";
    }

    @GetMapping("/allres")
    @ResponseBody
    public JSONObject allRes() {
        List<Resource> resources = null;
        resources = service.getAllRes();
        JSONObject jsonObject = new JSONObject();
        if (resources == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取资源列表失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取资源列表成功！");
            jsonObject.put("data", resources);
        }
        return jsonObject;
    }

    @ResponseBody
    @PostMapping("/uploadToFast")
    public JSONObject uploadRes (@RequestParam("file") MultipartFile file, @RequestParam(value = "uid") Integer uid) {
        String fallback = null;
        JSONObject jsonObject = new JSONObject();
        try {
            String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
            if ("jpg".equals(suffix) || "jpeg".equals(suffix) || "png".equals(suffix)) {
                fallback = service.upfileImage(file.getInputStream(), file.getSize(), suffix);
            } else {
                fallback = service.uploadFile(file);
            }
            String url = "http://134.175.68.126:9999/" + fallback;
            Resource resource = new Resource(file.getOriginalFilename(), 5, 0, 5.0, uid, url, "");
            service.uploadRes(resource);
            jsonObject.put("code", 200);
            jsonObject.put("msg", "上传成功！");
            jsonObject.put("data", url);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", 400);
            jsonObject.put("msg", "上传失败！");
            jsonObject.put("data", fallback);
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
        if (res != null) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取成功！");
            jsonObject.put("data", res);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "资源不存在！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @ResponseBody
    @GetMapping("/res/download/{rid}/user/{uid}")
    public JSONObject downloadRes(@PathVariable("rid") Integer rid, @PathVariable("uid") Integer uid) {
        return service.downloadRes(rid, uid);
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

    @ResponseBody
    @GetMapping("/res/del/{rid}")
    public JSONObject delRes2 (@PathVariable("rid") Integer rid) {
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

    @ResponseBody
    @GetMapping("/my/res/{uid}")
    public JSONObject getResByUid (@PathVariable("uid") Integer uid) {
        List<Resource> resources = null;
        resources = service.getAllResByUid(uid);
        JSONObject jsonObject = new JSONObject();
        if (resources == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取资源列表失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取资源列表成功！");
            jsonObject.put("data", resources);
        }
        return jsonObject;
    }
}
