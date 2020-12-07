package com.xrca.java8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xrca
 * @description 苹果
 * @date 2020/11/6 22:31
 **/
@Data
@AllArgsConstructor
public class Apple implements Fruit {
    private double weight;
}
