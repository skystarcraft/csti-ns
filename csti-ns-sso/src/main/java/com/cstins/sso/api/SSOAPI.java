package com.cstins.sso.api;

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
@RequestMapping("/sso")
public class SSOAPI {

    @Autowired
    private SsoService ssoService;

    @Autowired
    private TXMessageService txMessageService;

    @GetMapping("/user/token/{token}")
    public User getUserByToken(@PathVariable("token") String token) {
        return ssoService.getUserByToken(token);
    }

    @PostMapping("/user/register")
    public boolean register(@RequestBody User user) {
        return ssoService.register(user);
    }

    @PostMapping("/user/login")
    public String login(@RequestBody LoginUser loginUser) {
        return ssoService.userLogin(loginUser.getUid(), loginUser.getUser_password());
    }

    @GetMapping("/user/logout/{token}")
    public String login(@PathVariable("token") String token) {
        return ssoService.userLogout(token);
    }

    /**
     * 发送验证码
     * @param phone
     * @return 成功返回验证码
     */
    @GetMapping("/user/vcode/{phone}")
    public String sendVcode(@PathVariable("phone") String phone) {
        if (phone.length() != 11) {
            return "手机号格式不正确";
        } else {
            return txMessageService.send(phone);
        }
    }
}
