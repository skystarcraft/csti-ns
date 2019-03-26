package com.cstins.manager.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@Controller
public class Index {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", "awsl");
        return "index";
    }

}
