package com.xrca.juc;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author xrca
 * @description 设置Daemon属性必须在线程启动前
 * @date 2020/11/6 22:31
 **/
public class TestDaemon {
    public static void main(String[] args) throws Exception {
        IntStream.range(1, 100).forEach(i -> System.out.println(i));
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("====================");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        //t1.setDaemon(true);
        TimeUnit.SECONDS.sleep(3);

        IntStream.range(1, 100).forEach(i -> System.out.println(i));

    }
}
