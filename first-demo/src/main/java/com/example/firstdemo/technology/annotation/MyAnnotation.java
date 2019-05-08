package com.example.firstdemo.technology.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 注解
 */
//@Target(ElementType.TYPE)//注解使用范围
@Retention(RetentionPolicy.RUNTIME)//

public @interface MyAnnotation {

//    String name = "";
//    int age = 0;

    String name();

    int age();

}
