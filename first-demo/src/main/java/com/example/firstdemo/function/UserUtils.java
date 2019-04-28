package com.example.firstdemo.function;

import com.example.firstdemo.annotation.MyAnnotation;

public class UserUtils {

    @MyAnnotation(name = "user", age = 10)
    public static void add(String name, int age) {
        System.out.println("name:" + name + "\n"
                + "age:" + age);
    }

}
