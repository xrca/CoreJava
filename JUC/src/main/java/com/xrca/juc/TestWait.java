package com.xrca.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xrca
 * @description 测试wait
 * @date 2020/11/6 22:31
 **/
public class TestWait {
    List<Integer> list = new ArrayList<>();

    public synchronized void put(Integer i) {
        System.out.println(Thread.currentThread().getName() + "进来了，准备判断元素个数");
        if (list.size() == 3) {
            System.out.println(Thread.currentThread().getName() + "满了，我就在里面等着");
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "醒了，再睡一会儿之后添加元素");
        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "准备添加元素");
        list.add(i);
        System.out.println(Thread.currentThread().getName() + "添加完了");
        list.stream().forEach(System.out::println);
        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "收工");
    }

    public static void main(String[] args) throws Exception {
        TestWait tw = new TestWait();
        tw.list.add(1);
        tw.list.add(2);
        tw.list.add(3);
        Thread t1 = new Thread(() -> tw.put(4), "小明");
        t1.start();
        Thread t2 = new Thread(() -> tw.put(5), "小红");
        t2.start();

        TimeUnit.SECONDS.sleep(3);

        tw.list.remove(2);
        synchronized (tw) {
            tw.notifyAll();
        }

        t1.join();
        t2.join();

        System.out.println("===== 结束 =====");
    }
}
