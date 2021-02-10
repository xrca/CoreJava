package com.xrca.juc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author jiangxiaoyong
 * @date 2021/1/23 11:55
 */
public class MyLock {

    //1.自定义一个变量state  state=1 表示已加锁  state=0说明未加锁
    //2.控制自定义变量的争用  CAS
    //3.自定义一个队列  争用失败的线程加入到队列中
    //4.将线程park到队列中  轮到队列的时候将其唤醒

    private volatile int state;

    private static Unsafe unsafe;

    private static Long stateOffset;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            stateOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    private static class Node {
        volatile Thread thread;

        volatile Node pre;

        volatile Node next;

        Node(Thread t, Node pre) {
            thread = t;
            this.pre = pre;
        }

    }
    //链表的头
    private volatile Node headNode;
    //链表的尾
    private volatile Node tailNode;

    private static Long tailOffset;

    static {
        try {
            tailOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("tailNode"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    public boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    //获取到竞争的直接返回  否则加入到一个队列中 将线程阻塞
    public void lock() {
        if (compareAndSetState(0, 1)) {
            return;
        }
        //未成功的入队列
        Node node = enqueue();
        Node pre = node.pre;
        while (pre!= headNode || !compareAndSetState(0, 1)) {
            unsafe.park(false, 0L);
        }
        headNode = node;
        node.thread = null;
        node.pre = null;
        pre.next = null;
    }

    public void unlock() {
        state = 0;
        if (headNode != null) {
            Node next = headNode.next;
            if (next != null) {
                unsafe.unpark(next.thread);
            }
        }
    }

    //入队列的时候 需要使用CAS
    private Node enqueue() {
        while (true) {
            if (tailNode != null) {
                Node t = tailNode;
                Node node = new Node(Thread.currentThread(), t);
                if (compareAndSetTail(t, node)) {
                    t.next = node;
                    return node;
                }
            } else {
                Node node = new Node(Thread.currentThread(), null);
                if (compareAndSetTail(null, node)) {
                    tailNode = node;
                    headNode = node;
                    return node;
                }
            }
        }
    }


}
