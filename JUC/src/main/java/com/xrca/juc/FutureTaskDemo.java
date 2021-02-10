package com.xrca.juc;

import java.util.concurrent.*;

public class FutureTaskDemo {
    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> System.out.println(), 1);
        new Thread(futureTask).start();
        Integer result = futureTask.get();
        System.out.println("结果：" + result);

        FutureTask<Integer> futureTask2 =
                new FutureTask<>(() -> ((int) (Math.random() * 100)));

        new Thread(futureTask2).start();
        Integer result2 = futureTask2.get();
        System.out.println("结果：" + result2);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        new Thread(() -> System.out.println("111")).start();

    }
}


class MyThread extends Thread {

}