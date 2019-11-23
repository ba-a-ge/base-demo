package com.example.firstdemo.bean;

public class Person {
    //萨阿丹给
    String name;
    private int age;
    public boolean sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void sayName() {
        System.out.println(name);
    }

    public void sayAge() {
        System.out.println(age);
    }

    public void saySex() {
        System.out.println(sex);
    }


    /*
            以下是构造器
             */
    public Person() {
        super();
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(boolean sex) {
        this.sex = sex;
    }

    public Person(int age) {
        this.age = age;
    }


    public Person(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}

