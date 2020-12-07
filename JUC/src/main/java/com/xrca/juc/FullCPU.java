package com.xrca.juc;

/**
 * @author xrca
 * @description 让CPU占用率保持100%
 * @date 2020/11/6 22:31
 **/
public class FullCPU {
    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < processors; i++) {
            new Thread(() -> {
                while (true) {

                }
            }).start();
        }
    }
}
