package com.yuanqing.concurrent.chapter01;

import java.util.concurrent.TimeUnit;

public class TryConcurrent {


    public static void main(String[] args) {

        new Thread(TryConcurrent::browseNews).start();
        enjoyMusic();


    }

    private static void enjoyMusic() {
        for (; ; ) {
            System.out.println("Uh-huh, the nice music!");
            sleep(1);
        }
    }


    private static void browseNews() {

        for (; ; ) {
            System.out.println("Uh-huh, the good news!");
            sleep(1);
        }
    }


    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
