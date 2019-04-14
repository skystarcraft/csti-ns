package com.cstins.manager.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    public JSONObject getArticles() {
        JSONObject jsonObject = new JSONObject();
        List<Article> articles = managerService.getArticles();
        if (articles == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取文章列表失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取文章列表成功！");
            jsonObject.put("data", articles);
        }
        return jsonObject;
    }

    /**
     * 删除单个文章
     * @param aid
     * @return
     */
    @DeleteMapping("/article/{aid}")
    public JSONObject delArticle(@PathVariable("aid") Integer aid) {
        JSONObject jsonObject = new JSONObject();
        boolean b = managerService.delArticle(aid);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    /**
     * 删除多篇文章
     * @param list
     * @return
     */
    @DeleteMapping("/articles/{list}")
    public JSONObject delArticles(@PathVariable("list") String[] list) {
        JSONObject jsonObject = new JSONObject();
        boolean b = managerService.delArticles(list);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    /**
     * 获取所有标签
     * @return
     */
    @GetMapping("/tags")
    public JSONObject getTags() {
        JSONObject jsonObject = new JSONObject();
        List<Tags> tags = managerService.getTags();
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

    /**
     * 增加标签
     * @param tag
     * @return
     */
    @PostMapping("/tag")
    public JSONObject addTag (@RequestBody Tags tag) {
        JSONObject jsonObject = new JSONObject();
        boolean b = managerService.addTag(tag.getTag_name());
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "添加成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "添加失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    /**
     * 删除单个标签
     * @param tid
     * @return
     */
    @DeleteMapping("/tag/{tid}")
    public JSONObject delOneTag(@PathVariable("tid") Integer tid) {
        JSONObject jsonObject = new JSONObject();
        boolean b = managerService.delTag(tid);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    /**
     * 删除多个标签
     * @param list
     * @return
     */
    @DeleteMapping("/tags/{list}")
    public JSONObject delTags(@PathVariable("list") String[] list) {
        JSONObject jsonObject = new JSONObject();
        boolean b = managerService.delTags(list);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    /**
     * 获取所有用户
     * @return
     */
    @GetMapping("/allusers")
    public JSONObject getAllUser() {
        JSONObject jsonObject = new JSONObject();
        List<User> users = managerService.getAllUser();
        if (users == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取列表失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取列表成功！");
            jsonObject.put("data", users);
        }
        return jsonObject;
    }

    /**
     * 获取组内成员
     * @return
     */
    @GetMapping("/groupuser")
    public JSONObject getGroupUser() {
        JSONObject jsonObject = new JSONObject();
        List<User> users = managerService.getUsers();
        if (users == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取列表失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取列表成功！");
            jsonObject.put("data", users);
        }
        return jsonObject;
    }

    /**
     * 将单个用户提升为组内成员
     * @param uid
     * @return
     */
    @GetMapping("/changeuser/{uid}")
    public JSONObject changeType(@PathVariable("uid") Integer uid) {
        JSONObject jsonObject = new JSONObject();
        boolean b = managerService.Elevate_permissions(uid);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "提权成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "提权失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    /**
     * 将多个用户提升为组内成员
     * @return
     */
    @PostMapping("/changeusers/{list}")
    public JSONObject changeTypes(@PathVariable("list") String[] list) {
        JSONObject jsonObject = new JSONObject();
        boolean b = managerService.Elevate_permissions(list);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "提权成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "提权失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    /**
     * 删除用户
     * @param uid
     * @return
     */
    @DeleteMapping("/user/{uid}")
    public JSONObject delUser(@PathVariable("uid") Integer uid) {
        JSONObject jsonObject = new JSONObject();
        boolean b = managerService.delUser(uid);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    /**
     * 批量删除用户
     * @param uid
     * @return
     */
    @DeleteMapping("/users/{uid}")
    public JSONObject delUsers(@PathVariable("uid") String[] uid) {
        JSONObject jsonObject = new JSONObject();
        boolean b = managerService.delUsers(uid);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    /**
     * 获取前台显示的信息
     * @return
     */
    @GetMapping("/about")
    public JSONObject getIntroduction() {
        Front front = frontService.findFront();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "获取成功！");
        jsonObject.put("data", front);
        return jsonObject;
    }

    /**
     * 编辑信息
     * @param front
     * @return
     */
    @PutMapping(value = "/about")
    public JSONObject updateIntroduction(@RequestBody Front front) {
        JSONObject jsonObject = new JSONObject();
        boolean b = frontService.updateFront(front);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "修改成功！");
            jsonObject.put("data", front);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "修改失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    /**
     * 获取所有链接
     * @return
     */
    @GetMapping("/links")
    public JSONObject getLinks() {
        List<Link> links = linkService.getLinks();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "获取成功！");
        jsonObject.put("data", links);
        return jsonObject;
    }

    /**
     * 增加链接
     * @param link
     * @return
     */
    @PostMapping("/link")
    public JSONObject addLink(@RequestBody Link link) {
        JSONObject jsonObject = new JSONObject();
        boolean b = linkService.addLink(link);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "添加成功！");
            jsonObject.put("data", link);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "添加失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    /**
     * 修改链接
     * @param link
     * @return
     */
    @PutMapping("/link")
    public JSONObject updateLink(@RequestBody Link link) {
        JSONObject jsonObject = new JSONObject();
        boolean b = linkService.updateLink(link);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "修改成功！");
            jsonObject.put("data", link);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "修改失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    /**
     * 删除链接
     * @param link
     * @return
     */
    @DeleteMapping("/link")
    public JSONObject delLink(@RequestBody Link link) {
        JSONObject jsonObject = new JSONObject();
        boolean b = linkService.delLnk(link);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @DeleteMapping("/link/{id}")
    public JSONObject delLink(@PathVariable("id") Integer id) {
        JSONObject jsonObject = new JSONObject();
        boolean b = linkService.delLnk(id);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @DeleteMapping("/links/{lid}")
    public JSONObject delLinks(@PathVariable("lid") String[] lid) {
        JSONObject jsonObject = new JSONObject();
        boolean b = linkService.delLinks(lid);
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "删除成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "删除失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @GetMapping("/user/token/{token}")
    public JSONObject getUserByToken(@PathVariable("token") String token) {
        JSONObject jsonObject = new JSONObject();
        User user = loginService.getUserByToken(token);
        if (user == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取token失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取成功！");
            jsonObject.put("data", user);
        }
        return jsonObject;
    }

    @PostMapping("/user/login")
    public JSONObject login(@RequestBody LoginUser loginUser) {
        JSONObject jsonObject = new JSONObject();
        String login = loginService.userLogin(loginUser.getUid(), loginUser.getUser_password());
        if ("0".equals(login)) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "权限不足！");
            jsonObject.put("data", "");
        } else if ("1".equals(login)) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "用户不存在！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取成功！");
            jsonObject.put("data", login);
        }
        return jsonObject;
    }

    @GetMapping("/user/logout/{token}")
    public JSONObject login(@PathVariable("token") String token) {
        JSONObject jsonObject = new JSONObject();
        String logout = loginService.userLogout(token);
        if ("1".equals(logout)) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "注销成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "注销失败！");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

    @GetMapping("/apply")
    public JSONObject getAllApplys() {
        JSONObject jsonObject = new JSONObject();
        List<User> users = managerService.getApplyUser();
        if (users == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取列表失败！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取列表成功！");
            jsonObject.put("data", users);
        }
        return jsonObject;
    }

    @GetMapping("/all")
    public JSONArray getAll() {
        JSONArray jsonArray = new JSONArray();
        List<User> users = managerService.getAllUser();
        List<Article> articles = managerService.getArticles();
        List<Tags> tags = managerService.getTags();
        List<Link> links = linkService.getLinks();
        Front front = frontService.findFront();
//        jsonArray.add(new JSONObject().put("users", users));
//        jsonArray.add(new JSONObject().put("articles", articles));
//        jsonArray.add(new JSONObject().put("tags", tags));
//        jsonArray.add(new JSONObject().put("links", links));
//        jsonArray.add(new JSONObject().put("front", front));
        jsonArray.add(users);
        jsonArray.add(articles);
        jsonArray.add(tags);
        jsonArray.add(links);
        jsonArray.add(front);
        return jsonArray;
    }
}
