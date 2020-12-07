package com.xrca.java8.lambda;

import com.xrca.java8.entity.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @author xrca
 * @description java8的lambda表达式示例
 * @date 2020/11/6 22:31
 **/
public class LambdaDemo {



    /**
     * @author xrca
     * @description 将list转化为map
     * @date 2020/11/14 12:11
     **/
    @Test
    public void lambda1() {
        List<Student> students = new ArrayList<>();
        // add some students

        // 示例1：最常见的写法
        Map<String, Student> studentMap1 = students.stream().collect(Collectors.toMap(Student::getStudentNo, s -> s));

        // 示例2：对key做简单的字符串拼接操作
        Map<String, Student> studentMap2 = students.stream()
                .collect(Collectors.toMap(stu -> stu.getStudentNo() + "-" + stu.getName(), s -> s));

        // 示例3：对key做复杂的处理
        Map<String, Student> studentMap3 = students.stream()
                .collect(Collectors.toMap(
                        // map的key，此处可以做更多的处理；若一行代码可以实现，就参考示例2，使用lambda方式简写
                        stu -> {
                            String key;
                            if (stu.getAge() >= 10) {
                                key = stu.getStudentNo() + "-" + stu.getName();
                            } else {
                                key = stu.getStudentNo() + "+" + stu.getName();
                            }
                            return key;
                        },
                        // map的value，同理此处也可以加代码块{}后进行复杂处理
                        s -> {
                            if (s.getAge() < 12) {
                                s.setType("child");
                            }
                            return s;
                        }));
    }

    @Test
    public void test() throws Exception {
        String str = "hello";
        //Thread.sleep(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(str);
            }
        }).start();
        System.out.println(1191 + 2135 + 1811 + 1488 + 1407 + 1052 + 785 + 1037 + 1311 + 1065 + 1787 + 1365 + 1241 + 940 + 1338 + 1570 + 1205 + 664 + 385 + 706 + 1350 + 1288 + 1248 + 1265 + 481 + 1284 + 339 + 954 + 1343 + 262 + 1502 + 1902 + 1920 + 1153 + 1271);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("ok"));

        cyclicBarrier.await();
    }

    @Test
    public void testCPU() {
        // CPU密集型运算
        System.out.println(Runtime.getRuntime().availableProcessors());

        //IO密集型
        System.out.println(Runtime.getRuntime().totalMemory());

        ReentrantLock reentrantLock = new ReentrantLock();

        reentrantLock.lock();

        CountDownLatch countDownLatch = new CountDownLatch(6);

        try {
            //FuturetA\
            //DAEMON
            int[] arr = new int[]{1, 2, 3, 4, 5};

            System.out.println(Arrays.stream(arr).sum());
            List<Integer> nums = new ArrayList<>();
            nums.add(1);
            nums.add(2);

            System.out.println(nums.stream().mapToInt(Integer::intValue).sum());

            Executors.newWorkStealingPool();
            //volatile int i = 1;
        } finally {
        }
    }
}