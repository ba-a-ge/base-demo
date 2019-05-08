package com.example.firstdemo.technology.genericity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GenericityPojoTest {
    GenericityPojo<Number> pojo = null;

    @Before
    public void before() {
        pojo = new GenericityPojo<>();
    }


    /**
     * 泛型通配符下限测试
     */
    @Test
    public void test4() {
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(33);
//        list.add(44);
//        list.add(55);
//        pojo.showSuper(list);

        ArrayList<Object> list = new ArrayList<>();
        list.add(33);
        list.add(44.00);
        list.add("55");
        pojo.showSuper(list);
    }


    /**
     * 泛型通配符上限测试
     */
    @Test
    public void test3() {
//        ArrayList<String> list = new ArrayList<>();
//        list.add("11");
//        list.add("12");
//        list.add("13");
//        pojo.showExtends(list);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(33);
        list.add(44);
        list.add(55);
        pojo.showExtends(list);
    }


    /**
     * 泛型通配符测试
     */
    @Test
    public void test2() {
        ArrayList<String> list = new ArrayList<>();
        list.add("11");
        list.add("12");
        list.add("13");
        pojo.showTongPeiFu(list);
    }


    /**
     * 测试泛型方法  基于 @Before 方法创建的pojo,GenericityPojo<Number> pojo; Number 为入参
     */
    @Test
    public void test1() {
        pojo.show(15937);
    }

}