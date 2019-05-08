package com.example.firstdemo.technology.annotation;


/**
 * 注解使用工具
 */
public class AnnotationFunction {

    @MyAnnotation(name = "user", age = 10)
    public static void add(String name, int age) {
        System.out.println("name:" + name + "\n"
                + "age:" + age);
    }

}
