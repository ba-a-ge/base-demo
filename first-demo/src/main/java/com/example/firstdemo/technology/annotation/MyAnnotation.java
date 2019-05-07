package com.example.firstdemo.technology.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


//@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface MyAnnotation {

//    String name = "";
//    int age = 0;

    String name();

    int age();

}
