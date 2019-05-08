package com.example.firstdemo.technology.annotation;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class AnnotationFunctionTest {

    @Test
    public void test1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        Class<? extends AnnotationFunction> aClass = new AnnotationFunction().getClass();

        Method add = aClass.getMethod("add", String.class, int.class);

        MyAnnotation annotation = add.getAnnotation(MyAnnotation.class);

        int age = annotation.age();
        String name = annotation.name();

        AnnotationFunction annotationFunction = aClass.newInstance();

        add.invoke(annotationFunction,name,age);


    }

}