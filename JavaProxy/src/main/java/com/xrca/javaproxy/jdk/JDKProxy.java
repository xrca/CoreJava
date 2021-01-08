package com.xrca.javaproxy.jdk;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xrca
 * @description jdk实现动态代理
 * @date 2020-12-25 15:38
 **/
@AllArgsConstructor
public class JDKProxy {

    // 维护一个委托类
    private Object target;

    // 为委托类生成代理对象
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开启事务...");
                        Object result = method.invoke(target, args);
                        System.out.println("关闭事务...");
                        return null;
                    }
                });
    }
}
