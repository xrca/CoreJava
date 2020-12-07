package com.xrca.java8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xrca
 * @description 香蕉
 * @date 2020/12/7 22:31
 **/
@Data
@AllArgsConstructor
public class Banana implements Fruit {
    private Double weight;
}
