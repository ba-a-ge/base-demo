package com.yuanqing.testdemo;

public abstract class Build {

    public Build(String string) {
        this.string = string;
    }

    private String string;

    void test2() {
        test();
    }

    void test() {
        this.test2();

    }


}
