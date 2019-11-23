package com.example.ranchertestemptydemo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/rancher")
public class TestController {

    @RequestMapping("/test1")
    public String test1() {
        return "admin/test1";
    }

}
