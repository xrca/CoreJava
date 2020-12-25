package com.xrca.java8.functional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
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

    public static void main(String[] args) {
        System.out.println(transfer(1256));
        System.out.println(transfer(933));
        System.out.println(transfer(2148));
        System.out.println(transfer(2049));
        System.out.println(transfer(624));
        System.out.println(transfer(1381));
        System.out.println(transfer(1811));
        System.out.println(transfer(462));
        System.out.println(transfer(1074));
        System.out.println(transfer(842));
        System.out.println(transfer(560));
        System.out.println(transfer(789));
        System.out.println(transfer(1042));
        System.out.println(transfer(579));
        System.out.println(transfer(472));
        System.out.println(transfer(505));
        System.out.println(transfer(2752));
        System.out.println(transfer(2469));
        System.out.println(transfer(2960));
        System.out.println(transfer(2801));
        System.out.println(transfer(516));
        System.out.println(transfer(2040));
        System.out.println(transfer(1680));
        System.out.println(transfer(308));
        System.out.println(transfer(518));
        System.out.println(transfer(894));
        System.out.println(transfer(431));
        System.out.println(transfer(919));

        //new LinkedHashMap<>(12, 0.75, true);
    }

    public static String transfer(int duration) {
        int minute = duration / 60;
        int second = duration % 60;
        return minute + "分" + second + "秒";
    }
}
