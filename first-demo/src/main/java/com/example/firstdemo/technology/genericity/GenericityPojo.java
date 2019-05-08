package com.example.firstdemo.technology.genericity;


import java.util.List;

/**
 * 泛型
 */
public class GenericityPojo<T> {


    /**
     * 一般泛型方法
     *
     * @param t
     * @param <T>
     */
    public <T> void show(T t) {
        System.out.println(t);
    }


    /**
     * 通配符使用
     *
     * @param list
     */
    public void showTongPeiFu(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


    /**
     * 泛型的通配符上限，只能使用 number 或其子类作为入参。
     *
     * @param list
     */
    public void showExtends(List<? extends Number> list) {

        for (Number n : list
        ) {
            System.out.println(n);
        }

    }


    /**
     * 泛型的通配符下限，只能使用 number 或其父类作为入参。
     * @param list
     */
    public void showSuper(List<? super T> list) {

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

    

}
