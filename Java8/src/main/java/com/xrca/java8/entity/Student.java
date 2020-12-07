package com.xrca.java8.entity;

import lombok.Data;

/**
 * @author xrca
 * @description 学生类
 * @date 2020/11/6 22:31
 **/
@Data
public class Student {
    private Long studentId;

    private String studentNo;

    private Integer age;

    private String name;

    private String type;
}
