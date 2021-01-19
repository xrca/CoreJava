package com.xrca.reflection;

import com.xrca.reflection.entity.Computer;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionDemo {

    @Test
    public void testReflection1() throws Exception {
        Class clazz = Computer.class;
        // 通过反射创建类
        Constructor<Computer> constructor = clazz.getConstructor();
        Computer computer = constructor.newInstance();
        computer.start();

        clazz.getDeclaredConstructor();

        // 通过反射获取属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        // 获取公有属性
        fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        Field field = clazz.getDeclaredField("cpu");
        field.setAccessible(true);
        field.set(computer, "Ryzen5 3600");
        System.out.println(computer.getCpu());

        // 反射调用方法
        Method[] m1s = clazz.getMethods();
        Method[] m2s = clazz.getDeclaredMethods();

        Method method = clazz.getDeclaredMethod("getCpu");
        System.out.println(method.invoke(computer));

        Method method2 = clazz.getDeclaredMethod("calc");
        method2.setAccessible(true);
        method2.getModifiers();
        method.getGenericReturnType();
        method2.getName();
        System.out.println(method2.invoke(computer));
    }

    @Test
    public void testGetClass() throws Exception {
        // 1
        Class<Computer> class1 = Computer.class;

        // 2
        Computer computer = new Computer();
        Class class2 = computer.getClass();

        // 3
        Class class3 = Class.forName("com.xrca.reflection.entity.Computer");

        // 4
        ClassLoader classLoader = ReflectionDemo.class.getClassLoader();
        Class class4 = classLoader.loadClass("com.xrca.reflection.entity.Computer");
        System.out.println(class1 == class2);
        System.out.println(class2 == class3);
        System.out.println(class3 == class4);

        class1.newInstance();
    }
}
