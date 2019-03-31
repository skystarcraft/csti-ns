package com.cstins.sso.api;

import com.alibaba.fastjson.JSONObject;
import com.cstins.sso.entity.LoginUser;
import com.cstins.sso.entity.User;
import com.cstins.sso.service.SsoService;
import com.cstins.sso.service.TXMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@RestController
@CrossOrigin
public class SSOAPI {

    @Autowired
    private SsoService ssoService;

    @Autowired
    private TXMessageService txMessageService;

    @GetMapping("/")
    public JSONObject index() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "请登录!");
        jsonObject.put("data", "");
        return jsonObject;
    }

    @CrossOrigin
    @GetMapping("/user/token/{token}")
    public JSONObject getUserByToken(@PathVariable("token") String token) {
        User user = ssoService.getUserByToken(token);
        JSONObject jsonObject = new JSONObject();
        if (user == null) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "获取token失败");
            jsonObject.put("data", "请重新登陆");
        } else {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "获取成功！");
            jsonObject.put("data", user);
        }
        return jsonObject;
    }

    @CrossOrigin
    @PostMapping("/user/register")
    public JSONObject register(@RequestBody User user) {
        boolean b = ssoService.register(user);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "注册成功！");
            jsonObject.put("data", user);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "注册失败！");
            jsonObject.put("data", user);
        }
        return jsonObject;
    }

    @CrossOrigin
    @PostMapping("/user/login")
    public JSONObject login(@RequestBody LoginUser loginUser) {
        String token = ssoService.userLogin(loginUser.getUid(), loginUser.getUser_password());
        JSONObject jsonObject = new JSONObject();
        if (!"failed".equals(token)) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "登陆成功！");
            jsonObject.put("data", token);
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "登陆失败！");
            jsonObject.put("data", "用户名或密码错误！");
        }
        return jsonObject;
    }

    @CrossOrigin
    @GetMapping("/user/logout/{token}")
    public String login(@PathVariable("token") String token) {
        return ssoService.userLogout(token);
    }

    /**
     * 发送验证码
     * @param phone
     * @return 成功返回验证码
     */
    @CrossOrigin
    @GetMapping("/user/vcode/{phone}")
    public JSONObject sendVcode(@PathVariable("phone") String phone) {
        JSONObject jsonObject = new JSONObject();
        if (phone.length() != 11) {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "手机号格式不正确！");
            jsonObject.put("data", "");
        } else {
            String send = txMessageService.send(phone);
            jsonObject.put("code", 200);
            jsonObject.put("msg", "发送验证码成功！");
            jsonObject.put("data", send);
        }
        return jsonObject;
    }
}
