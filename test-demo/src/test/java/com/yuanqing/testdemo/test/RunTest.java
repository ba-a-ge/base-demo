package com.yuanqing.testdemo.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RunTest {
    @Value("${hahaha}")
    String hahaha;

    public void setHahaha() {
        System.out.println(this.hahaha);
    }
}
