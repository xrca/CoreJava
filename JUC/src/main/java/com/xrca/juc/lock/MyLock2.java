package com.xrca.juc.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author jiangxiaoyong
 * @date 2021/1/23 11:55
 */
public class MyLock2 {

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
            stateOffset = unsafe.objectFieldOffset(MyLock2.class.getDeclaredField("state"));
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
        Thread thread;

        Node pre;

        Node next;

        Node(Thread t, Node pre) {
            thread = t;
            this.pre = pre;
        }

        @Override
        public String toString() {
            Node n = this;
            StringBuilder sb = new StringBuilder();
            while(n != null) {
                if (n.thread != null) {
                    sb.append(n.thread.getName());
                    sb.append(" -> ");
                }
                n = n.next;
            }
            return sb.toString();
        }
    }

    //链表的头
    public Node headNode;
    //链表的尾
    public Node tailNode;

    private static Long tailOffset;

    static {
        try {
            tailOffset = unsafe.objectFieldOffset(MyLock2.class.getDeclaredField("tailNode"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    public boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    //获取到竞争的直接返回  否则加入到一个队列中 将线程阻塞
    public void lock() {
        Thread currT = Thread.currentThread();
        if (compareAndSetState(0, 1)) {
            return;
        }
        //未成功的入队列
        Node node = enqueue();
        while (node != headNode || !compareAndSetState(0, 1)) {
            System.out.println("挂起线程>>>>>>>>>>" + currT.getName() + "当前线程队列>>>>>>>>>>" + headNode);
            System.out.println();
            unsafe.park(false, 0L);
        }

        System.out.println();
        /*headNode = node;
        node.thread = null;
        node.pre = null;
        //node.next = null;*/
    }

    public void unlock() {
        state = 0;
        Thread ct = Thread.currentThread();
        if (headNode != null) {
            Thread hT = headNode.thread;
            // 队头是当前线程
            if (headNode.thread == Thread.currentThread()) {
                if (headNode.next != null) {
                    Node oldhead = headNode;
                    headNode = headNode.next;
                    oldhead.thread = null;
                    oldhead.next = null;
                    oldhead.pre = null;
                    //
                    headNode.pre = null;
                    //unsafe.unpark(headNode.thread);
                }
            } else {
                // 队头不是当前线程
                if (headNode != null) {
                    //unsafe.unpark(headNode.thread);
                }
            }
        }
        if (headNode == null) {
            System.out.println();
        }
    }

    //入队列的时候 需要使用CAS
    private Node enqueue() {
        while (true) {
            Thread cT = Thread.currentThread();
            if (tailNode!=null) {
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
