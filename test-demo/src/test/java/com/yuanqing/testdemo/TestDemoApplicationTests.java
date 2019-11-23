package com.yuanqing.testdemo;

import com.yuanqing.testdemo.aes.AES;
import com.yuanqing.testdemo.test.RunTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemoApplicationTests {


    @Autowired
    RunTest test;

    @Test
    public void contextLoads() {
        test.setHahaha();

    }

}
