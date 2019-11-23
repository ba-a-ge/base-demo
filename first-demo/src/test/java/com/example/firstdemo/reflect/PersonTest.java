package com.example.firstdemo.reflect;

import com.example.firstdemo.bean.Person;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class PersonTest {

    Class<? extends Person> aClass = null;

    @Before
    public void before() {
        aClass = Person.class;

    }


    @Test
    public void test3() {
        Field[] fields = aClass.getFields();
        for (Field f : fields
        ) {
            System.out.println(f.getName() + ":" + f);
            System.out.println();
        }
        System.out.println();
    }


    /**
     * 获取类对象所有方法
     */
    @Test
    public void test2() {

        Method[] declaredMethods = aClass.getDeclaredMethods();

        for (Method m : declaredMethods
        ) {
            System.out.println("name:" + m.getName());
            System.out.println("getAnnotatedReturnType:" + m.getAnnotatedReturnType());
            System.out.println("getDeclaredAnnotations:" + m.getDeclaredAnnotations());
            System.out.println("getDeclaringClass:" + m.getDeclaringClass());
            System.out.println("getDefaultValue:" + m.getDefaultValue());
            System.out.println("getExceptionTypes:" + m.getExceptionTypes());
            System.out.println("getGenericExceptionTypes:" + m.getGenericExceptionTypes());
            System.out.println("getGenericParameterTypes:" + m.getGenericParameterTypes());
            System.out.println("getModifiers:" + m.getModifiers());
            System.out.println("getParameterAnnotations:" + m.getParameterAnnotations());
            System.out.println("getReturnType:" + m.getReturnType());
            System.out.println();
//            name:sayName
//            getAnnotatedReturnType:void
//            getDeclaredAnnotations:[Ljava.lang.annotation.Annotation;@3444d69d
//            getDeclaringClass:class com.example.firstdemo.reflect.Person
//            getDefaultValue:null
//            getExceptionTypes:[Ljava.lang.Class;@1c72da34
//            getGenericExceptionTypes:[Ljava.lang.Class;@6b0c2d26
//            getGenericParameterTypes:[Ljava.lang.Class;@3d3fcdb0
//            getModifiers:1
//            getParameterAnnotations:[[Ljava.lang.annotation.Annotation;@641147d0
//            getReturnType:void


        }

    }


    /**
     * 获取Class的三种方式
     */
    @Test
    public void test1() {
        Class class1 = Person.class;

        Class<? extends Person> aClass = new Person().getClass();

        try {
            Class<?> aClass1 = Class.forName("com.example.firstdemo.bean.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("yq");

    }


}