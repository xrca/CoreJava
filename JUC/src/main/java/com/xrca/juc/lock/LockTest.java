package com.xrca.juc.lock;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author jiangxiaoyong
 * @date 2021/1/23 12:44
 */
public class LockTest {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        MyLock lock = new MyLock();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        IntStream.range(0,1001).forEach(i -> new Thread(() -> {
            lock.lock();
            System.out.println(++count);
            lock.unlock();
            countDownLatch.countDown();
        }).start());
        countDownLatch.await();
        System.out.println("==================");
        System.out.println(count);
    }
}
