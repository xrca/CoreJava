package com.xrca.java8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xrca
 * @description 学生类
 * @date 2020/12/7 22:31
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long studentId;

    private String studentNo;

    private Integer age;

    private String name;

    private String type;
}
