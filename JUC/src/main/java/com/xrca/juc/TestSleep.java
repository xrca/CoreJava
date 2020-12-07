package com.xrca.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author xrca
 * @description 测试线程启动顺序
 * @date 2020/11/6 22:31
 **/
public class TestSleep {
    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            System.out.println("1");
        }).start();
        TimeUnit.MILLISECONDS.sleep(10);

        new Thread(() -> {
            System.out.println("2");
        }).start();
        //TimeUnit.MILLISECONDS.sleep(10);
        Thread.sleep(1);

        new Thread(() -> {
            System.out.println("3");
        }).start();
        //TimeUnit.MILLISECONDS.sleep(10);
        Thread.sleep(1);

        new Thread(() -> {
            System.out.println("4");
        }).start();
        //TimeUnit.MILLISECONDS.sleep(10);
        Thread.sleep(1);

        new Thread(() -> {
            System.out.println("5");
        }).start();
        //TimeUnit.MILLISECONDS.sleep(10);
        Thread.sleep(1);

        new Thread(() -> {
            System.out.println("6");
        }).start();
        //TimeUnit.MILLISECONDS.sleep(10);
        Thread.sleep(1);

        new Thread(() -> {
            System.out.println("7");
        }).start();
        //TimeUnit.MILLISECONDS.sleep(10);
        Thread.sleep(1);

        new Thread(() -> {
            System.out.println("8");
        }).start();
        //TimeUnit.MILLISECONDS.sleep(10);
        Thread.sleep(1);

        new Thread(() -> {
            System.out.println("9");
        }).start();
        //TimeUnit.MILLISECONDS.sleep(10);
        Thread.sleep(1);

        new Thread(() -> {
            System.out.println("9");
        }).start();
        //TimeUnit.MILLISECONDS.sleep(10);
        Thread.sleep(1);

    }
}
