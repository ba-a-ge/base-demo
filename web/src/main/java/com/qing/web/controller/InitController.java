package com.qing.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhu_c_q
 * @version 1.0
 * @date 2020/9/9 13:48
 */
@Controller
@RequestMapping("/web")
public class InitController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


}
