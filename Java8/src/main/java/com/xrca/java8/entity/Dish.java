package com.xrca.java8.entity;

import lombok.Data;

/**
 * @author xrca
 * @description 菜肴
 * @date 2020-12-08 0:47
 **/
@Data
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * @author xrca
     * @description 菜肴品类，肉类，鱼类，其他
     * @date 2020/12/8 0:48
     **/
    public enum Type {
        MEAT,
        FISH,
        OTHER
    }
}
