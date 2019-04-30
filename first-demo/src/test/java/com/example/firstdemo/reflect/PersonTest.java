package com.example.firstdemo.reflect;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class PersonTest {


    @Test
    public void test2() {
        Class<? extends Person> aClass = Person.class;

        Method[] declaredMethods = aClass.getDeclaredMethods();

        System.out.println();

    }


    /**
     * 获取Class的三种方式
     */
    @Test
    public void test1() {
        Class class1 = Person.class;

        Class<? extends Person> aClass = new Person().getClass();

        try {
            Class<?> aClass1 = Class.forName("com.example.firstdemo.reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("yq");

    }


}