package com.xrca.java8.stream;

import com.xrca.java8.entity.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xrca
 * @description Stream示例
 * @date 2020-12-08 0:53
 **/
public class StreamDemo {
    public static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    @Test
    public void testStream1() {
        List<String> threeHighCaloriesDishNames= menu.stream()
                .filter(d -> d.getCalories() > 300) // 从流中排除某些元素，接受Predicate函数，同时返回一个Stream
                // map -> 将元素转换成其他形式或提取信息，返回一个Stream
                .map(Dish::getName)
                // 截断流，有点类似与mysql的limit
                .limit(3)
                // 转换
                .collect(Collectors.toList());
        System.out.println(threeHighCaloriesDishNames);
    }
}
