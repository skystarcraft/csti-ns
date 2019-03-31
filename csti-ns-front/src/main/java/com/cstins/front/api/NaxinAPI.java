package com.cstins.front.api;

import com.alibaba.fastjson.JSONObject;
import com.cstins.front.service.NaxinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/
@RestController
public class NaxinAPI {

    @Autowired
    private NaxinService naxinService;

    @GetMapping("/front/naxin/{uid}")
    public JSONObject naxin(@PathVariable("uid") Integer uid) {
        boolean b = naxinService.naxin(uid);
        JSONObject jsonObject = new JSONObject();
        if (b) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "报名成功！");
            jsonObject.put("data", "");
        } else {
            jsonObject.put("code", 400);
            jsonObject.put("msg", "报名失败！,当前不再纳新时间");
            jsonObject.put("data", "");
        }
        return jsonObject;
    }

}
