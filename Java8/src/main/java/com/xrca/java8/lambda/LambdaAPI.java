package com.xrca.java8.lambda;

import com.xrca.java8.entity.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xrca
 * @description Lambda表达式
 * @date 2020/12/7 15:42
 **/
public class LambdaAPI {

    @Test
    public void testStream() {
        List<Student> students = new ArrayList<>();
        students.stream();
        students.parallelStream();
    }

    /**
     * @author xrca
     * @description 使用AddSomeValue函数，自定addValue方法
     * @date 2020/12/7 15:44
     **/
    @Test
    public void testLambda() {
        Calc calc = new Calc();
        int result = calc.addValue(5, (value) -> {
            if (value % 2 == 1) {
                return value + 1;
            }
            return value;
        });
        System.out.println(result);

        int multi = calc.addValue(6, (value) -> value * 2);
        System.out.println(multi);
    }

}
