package com.xrca.javaproxy.cglib;

import com.xrca.javaproxy.dao.UserDao;
import lombok.AllArgsConstructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xrca
 * @description 使用CGlib生成动态代理
 * @date 2020-12-25 15:53
 **/
@AllArgsConstructor
public class CGLibProxy {
    // 委托类
    private UserDao target;

    public Object getProxyInstance() {
        // 工具类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("log before...");
                method.invoke(target, objects);
                System.out.println("log after...");
                return null;
            }
        });
        // 创建子类对象
        return enhancer.create();
    }
}
