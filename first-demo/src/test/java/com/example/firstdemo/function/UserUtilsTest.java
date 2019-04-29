package com.example.firstdemo.function;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class UserUtilsTest {

    @Test
    public void test1() {

        System.out.println("Fribonacci:" + Fribonacci(30));

    }


    /**
     * 斐波纳契序列：问题1：一列数的规则如下: 1、1、2、3、5、8、13、21、34 ，求第30位数是多少？使用递归实现
     *
     * @param n
     * @return
     */
    public static int Fribonacci(int n) {
        if (n <= 2)
            return 1;
        else
            return Fribonacci(n - 1) + Fribonacci(n - 2);

    }


    @Test
    public void test2() {
        int i = 4;
        String a = "A", b = "B", c = "C";
        hanio(i, a, b, c);
    }

    /**
     * 汉诺塔
     *
     * @param n
     * @param a
     * @param b
     * @param c
     */
    public static void hanio(int n, String a, String b, String c) {
        if (n == 1)
            System.out.println("移动'" + n + "'号盘子，从'" + a + "'-->'" + c + "';");
        else {
            hanio(n - 1, a, c, b);//把上面n-1个盘子从a借助b搬到c
            System.out.println("移动'" + n + "'号盘子，从'" + a + "'-->'" + c + "';");//紧接着直接把n搬动c
            hanio(n - 1, b, a, c);//再把b上的n-1个盘子借助a搬到c
        }
    }


}