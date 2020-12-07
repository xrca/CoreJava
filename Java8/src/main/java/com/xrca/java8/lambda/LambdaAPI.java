package com.xrca.java8.lambda;

import com.xrca.java8.entity.*;
import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

    /**
     * @author xrca
     * @description 构造函数引用
     * @date 2020/12/7 23:40
     **/
    public void testLambda2() {
        // 无参构造
        Supplier<Student> s1 = Student::new;
        Student student = s1.get();
        // 上述等价于
        Supplier<Student> s2 = () -> new Student();
        Student student2 = s1.get();


        // 有参构造
        Function<Double, Apple> f1 = Apple::new;
        Apple a1 = f1.apply(50.0);
        // 等价写法如下
        Function<Double, Apple> f2 = (weight) -> new Apple(weight);
        Apple a2 = f2.apply(50.0);

        // 两个参数构造函数
        BiFunction<String, Double, Orange> f3 = Orange::new;
        Orange o1 = f3.apply("yellow", 50.2);
        // 等价写法如下
        BiFunction<String, Double, Orange> f4 = (color, weight) -> new Orange(color, weight);
        Orange o2 = f4.apply("yellow", 50.2);

        //
        Map<String, Function<Double, Fruit>> map = new HashMap<>();
        map.put("apple", Apple::new);
        map.put("orange", Banana::new);

        Fruit apple = map.get("apple").apply(100.0);

        // 多个参数的接口可以进行自定义


    }

    /**
     * @author xrca
     * @description lambda表达式复合
     * @date 2020/12/8 0:11
     **/
    @Test
    public void testLambda3() {
        // 1、比较器复合
        Comparator<Orange> c1 = Comparator.comparing(Orange::getWeight).reversed().thenComparing(Orange::getWeight);

        // 2、谓词复合
        Predicate<Orange> moreThan100Orange = (orange) -> orange.getWeight() > 100.0;
        // 对上述取非
        Predicate<Orange> lessOrEqual100Orange = moreThan100Orange.negate();
        // 叠加
        Predicate<Orange> greenAnd100Orange = moreThan100Orange.and((orange) -> "green".equals(orange.getColor()));
        // or
        Predicate<Orange> yellowOr100Orange = moreThan100Orange.or((orange) -> "yellow".equals(orange.getColor()));

        // 测试
        System.out.println(yellowOr100Orange.test(new Orange("red", 50.2)));
        System.out.println(yellowOr100Orange.test(new Orange("red", 120.3)));
        System.out.println(yellowOr100Orange.test(new Orange("yellow", 50.2)));
        System.out.println(yellowOr100Orange.test(new Orange("yellow", 150.2)));

        // 3、函数复合

        // andThen类似与流水线
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        // g(f(x)) = (x + 1) * 2
        System.out.println(h.apply(5));

        Function<Integer, Integer> f2 = x -> x + 1;
        Function<Integer, Integer> g2 = x -> x * 2;
        Function<Integer, Integer> h2 = f2.compose(g2);
        // f(g(x)) = (x * 2) + 1
        System.out.println(h2.apply(5));
    }
}
