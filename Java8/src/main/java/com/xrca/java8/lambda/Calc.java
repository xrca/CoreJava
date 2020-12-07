package com.xrca.java8.lambda;

/**
 * @author xrca
 * @description 计算器，接受AddSomeValue函数
 * @date 2020/12/7 17:34
 **/
public class Calc {
    public int addValue(int value, AddSomeValue addSomeValue) {
        return addSomeValue.addSomeValue(value);
    }
}
