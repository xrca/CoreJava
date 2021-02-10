package com.xrca.juc.lock;

import com.xrca.juc.MyLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author jiangxiaoyong
 * @date 2021/1/23 12:44
 */
public class LockTest2 {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        //MyLock lock = new MyLock();
        MyLock lock = new MyLock();
        CountDownLatch countDownLatch = new CountDownLatch(21);
        IntStream.range(0,20).forEach(i -> {
            Runnable runnable = () -> {
                lock.lock();
                try {
                    //Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Thread ct = Thread.currentThread();
                //System.out.println(++count);
                System.out.println(Thread.currentThread().getName());
                lock.unlock();
                countDownLatch.countDown();
            };
            Thread t = new Thread(runnable);
            t.setName("T" + i);
            t.start();
        });
        countDownLatch.await();
        System.out.println("==================");
        System.out.println(count);
    }
}
