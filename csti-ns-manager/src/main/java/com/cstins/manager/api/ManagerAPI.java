package com.cstins.manager.api;

import com.cstins.manager.entity.*;
import com.cstins.manager.service.FrontService;
import com.cstins.manager.service.LinkService;
import com.cstins.manager.service.LoginService;
import com.cstins.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: csti-ns
 * @description: 后台接口
 * @author: 杨云龙
 **/

@RestController
@RequestMapping("/manager")
public class ManagerAPI {

    @Autowired
    private FrontService frontService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ManagerService managerService;

    /**
     * 获取文章列表
     * @return
     */
    @GetMapping("/articles")
    public List<Article> getArticles() {
        return managerService.getArticles();
    }

    /**
     * 删除单个文章
     * @param aid
     * @return
     */
    @DeleteMapping("/article/{aid}")
    public boolean delArticle(@PathVariable("aid") Integer aid) {
        return managerService.delArticle(aid);
    }

    /**
     * 删除多篇文章
     * @param list
     * @return
     */
    @DeleteMapping("/articles/{list}")
    public boolean delArticles(@PathVariable("list") String[] list) {
        return managerService.delArticles(list);
    }

    /**
     * 获取所有标签
     * @return
     */
    @GetMapping("/tags")
    public List<Tags> getTags() {
        return managerService.getTags();
    }

    /**
     * 增加标签
     * @param tag
     * @return
     */
    @PostMapping("/tag")
    public boolean addTag (@RequestBody Tags tag) {
        return managerService.addTag(tag.getTag_name());
    }

    /**
     * 删除单个标签
     * @param tid
     * @return
     */
    @DeleteMapping("/tag/{tid}")
    public boolean delOneTag(@PathVariable("tid") String[] tid) {
        return managerService.delTags(tid);
    }

    /**
     * 删除多个标签
     * @param list
     * @return
     */
    @DeleteMapping("/tags/{list}")
    public boolean delTags(@PathVariable("list") String[] list) {
        return managerService.delTags(list);
    }

    /**
     * 获取所有用户
     * @return
     */
    @GetMapping("/allusers")
    public List<User> getAllUser() {
        return managerService.getAllUser();
    }

    /**
     * 获取组内成员
     * @return
     */
    @GetMapping("/groupuser")
    public List<User> getGroupUser() {
        return managerService.getUsers();
    }

    /**
     * 将单个用户提升为组内成员
     * @param uid
     * @return
     */
    @PostMapping("/changeuser/{uid}")
    public boolean changeType(@PathVariable("uid") Integer uid) {
        return managerService.Elevate_permissions(uid);
    }

    /**
     * 将多个用户提升为组内成员
     * @return
     */
    @PostMapping("/changeusers/{list}")
    public boolean changeTypes(@PathVariable("list") String[] list) {
        return managerService.Elevate_permissions(list);
    }

    /**
     * 删除用户
     * @param uid
     * @return
     */
    @DeleteMapping("/user/{uid}")
    public boolean delUser(@PathVariable("uid") Integer uid) {
        return managerService.delUser(uid);
    }

    /**
     * 批量删除用户
     * @param uid
     * @return
     */
    @DeleteMapping("/users/{uid}")
    public boolean delUsers(@PathVariable("uid") String[] uid) {
        return managerService.delUsers(uid);
    }

    /**
     * 获取前台显示的信息
     * @return
     */
    @GetMapping("/about")
    public Front getIntroduction() {
        return frontService.findFront();
    }

    /**
     * 编辑信息
     * @param front
     * @return
     */
    @PutMapping(value = "/about")
    public boolean updateIntroduction(@RequestBody Front front) {
        if (front == null) return false;
        return frontService.updateFront(front);
    }

    /**
     * 获取所有链接
     * @return
     */
    @GetMapping("/links")
    public List<Link> getLinks() {
        return linkService.getLinks();
    }

    /**
     * 增加链接
     * @param link
     * @return
     */
    @PostMapping("/link")
    public boolean addLink(@RequestBody Link link) {
        if (link == null) return false;
        return linkService.addLink(link);
    }

    /**
     * 修改链接
     * @param link
     * @return
     */
    @PutMapping("/link")
    public boolean updateLink(@RequestBody Link link) {
        if (link == null) return false;
        return linkService.updateLink(link);
    }

    /**
     * 删除链接
     * @param link
     * @return
     */
    @DeleteMapping("/link")
    public boolean delLink(@RequestBody Link link) {
        if (link == null) return false;
        return linkService.delLnk(link);
    }

    @DeleteMapping("/links/{lid}")
    public boolean delLinks(@PathVariable("lid") String[] lid) {
        return linkService.delLinks(lid);
    }

    @GetMapping("/user/token/{token}")
    public User getUserByToken(@PathVariable("token") String token) {
        return loginService.getUserByToken(token);
    }

    @PostMapping("/user/login")
    public String login(@RequestBody LoginUser loginUser) {
        return loginService.userLogin(loginUser.getUid(), loginUser.getUser_password());
    }

    @GetMapping("/user/logout/{token}")
    public String login(@PathVariable("token") String token) {
        return loginService.userLogout(token);
    }
}
