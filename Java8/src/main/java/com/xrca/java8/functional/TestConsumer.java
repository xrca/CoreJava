package com.xrca.java8.functional;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author xrca
 * @description Consumer函数
 * @date 2020/12/7 18:02
 **/
public class TestConsumer {

    @Test
    public void testConsumer() {
        List<String> list = Arrays.asList("abcdefghijk", "com", "cn", "woaizhongguo", "nihao", "", "");
        Consumer<String> consumer = s -> {
            if (s.length() >= 5) {
                System.out.println(s);
            } else {
                System.out.println("too short!!!");
            }
        };
        forEach(list, consumer);
    }
    
    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        if (list != null) {
            for (T t : list) {
                consumer.accept(t);
            }
        }
    }
}
