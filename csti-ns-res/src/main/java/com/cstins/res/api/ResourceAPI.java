package com.cstins.res.api;

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
    public String uploadRes (@RequestParam("fileName") MultipartFile file) {
        String fallback = null;
        try {
            String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
            if ("jpg".equals(suffix) || "jpeg".equals(suffix) || "png".equals(suffix)) {
                fallback = service.upfileImage(file.getInputStream(), file.getSize(), suffix);
            } else {
                fallback = service.uploadFile(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fallback;
    }

    @ResponseBody
    @PostMapping("/res/resource")
    public boolean uploadRes2 (@RequestBody Resource resource) {
        return service.uploadRes(resource);
    }

    @ResponseBody
    @GetMapping("/res/{rid}")
    public Resource getRes(@PathVariable("rid") Integer rid) {
        return service.getRes(rid);
    }

    @ResponseBody
    @GetMapping("/res/download/{rid}/user/{uid}")
    public String downloadRes(@PathVariable("rid") Integer rid, @PathVariable("uid") Integer uid) {
        return service.downloadRes(rid, uid);
    }

    @ResponseBody
    @DeleteMapping("/res/{rid}")
    public boolean delRes (@PathVariable("rid") Integer rid) {
        return service.delRes(rid);
    }

}
