package com.xrca.java8.entity;

import lombok.Data;

/**
 * @author xrca
 * @description 橘子
 * @date 2020/11/6 22:31
 **/
@Data
public class Orange implements Fruit {
    private String color;

    private Double weight;

    public Orange(String color, Double weight) {
        this.color = color;
        this.weight = weight;
    }
}
