package com.xrca.java8.functional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author xrca
 * @description Predicate函数
 * @date 2020/12/7 22:29
 **/
public class TestPredicate {

    @Test
    public void testPredicate() {
        Predicate<String> lengthMoreThan10 = (s) -> s.length() > 10;
        List<String> list = Arrays.asList("abcdefghijk", "com", "cn", "woaizhongguo", "nihao", "", "");
        List<String> result = filter(list, lengthMoreThan10);
        result.forEach(System.out::println);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        if (list != null) {
            for (T s : list) {
                if (predicate.test(s)) {
                    result.add(s);
                }
            }
        }
        return result;
    }
}
