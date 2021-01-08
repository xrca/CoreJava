package com.xrca.javaproxy.cglib;

import lombok.AllArgsConstructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xrca
 * @description 通用代理类
 * @date 2020-12-27 12:27
 **/
@AllArgsConstructor
public class GeneralProxy {
    private Object target;

    public Object getProxyInstance() {
        // 工具类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("method before...");
                Object result = method.invoke(target, objects);
                System.out.println("method after...");
                return result;
            }
        });
        // 创建子类对象
        return enhancer.create();
    }
}
