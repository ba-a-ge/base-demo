package com.example.firstdemo.technology.annotation;


public class AnnotationUtils {

    @MyAnnotation(name = "user", age = 10)
    public static void add(String name, int age) {
        System.out.println("name:" + name + "\n"
                + "age:" + age);
    }

}
