package com.xrca.java8.functional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author xrca
 * @description Function函数
 * @date 2020/12/7 18:07
 **/
public class TestFunction {

    @Test
    public void testFunction() {
        List<String> list = Arrays.asList("abcdefghijk", "com", "cn", "woaizhongguo", "nihao", "xsa", "wqwe");
        Function<String, String> function = (s) -> "element -> " + s;
        List<String> result = iterator(list, function);
        result.forEach(System.out::println);
    }

    public static <T, R> List<R> iterator(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        if (list != null) {
            for (T t : list) {
                R r = function.apply(t);
                result.add(r);
            }
        }
        return result;
    }
}
