package com.xrca.java8.functional;

import com.xrca.java8.entity.Apple;
import org.junit.Test;

import java.util.function.Supplier;

/**
 * @author xrca
 * @description Supplier函数
 * @date 2020/12/7 22:31
 **/
public class TestSupplier {
    @Test
    public void testSupplier() {
        Supplier<Apple> supplier = () -> new Apple(Math.random() * 100);
        Apple apple = supplier.get();
    }
}
