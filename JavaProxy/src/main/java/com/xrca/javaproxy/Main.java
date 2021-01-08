package com.xrca.javaproxy;

import com.xrca.javaproxy.cglib.CGLibProxy;
import com.xrca.javaproxy.cglib.GeneralProxy;
import com.xrca.javaproxy.dao.UserDao;
import com.xrca.javaproxy.dao.impl.UserDaoImpl;
import com.xrca.javaproxy.jdk.JDKProxy;
import com.xrca.javaproxy.proxy.LogUserProxy;
import com.xrca.javaproxy.proxy.LogUserProxy2;
import com.xrca.javaproxy.util.ProxyUtil;
import net.sf.cglib.core.DebuggingClassWriter;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * @author xrca
 * @description TODO
 * @date 2020-12-25 12:15
 **/
public class Main {
    public static void main(String[] args) {
        // 将动态代理类持久化到磁盘上
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.getProperties().put(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\Users\\fishs\\workspace\\IdeaProjects\\CoreJava\\");

        //UserDaoImpl userDao = new LogUserProxy();
        //userDao.query("xrca");

        UserDao userDaoImpl = new UserDaoImpl();
        //UserDao proxy = new LogUserProxy2(userDaoImpl);
        //proxy.query("xrca");

        //UserDao userDaoProxy = (UserDao) ProxyUtil.newProxyInstance(userDaoImpl);
        //userDaoProxy.query("xrca");

        // jdk动态代理
        /*JDKProxy jdkProxy = new JDKProxy(userDaoImpl);
        UserDao javaProxy = (UserDao) jdkProxy.getProxyInstance();
        UserDao javaProxy2 = (UserDao) jdkProxy.getProxyInstance();
        System.out.println(javaProxy == javaProxy2);
        javaProxy.query("xrca");*/

        // cglib动态代理
        /*CGLibProxy cgLibProxy = new CGLibProxy(userDaoImpl);
        UserDao cfLibDao = (UserDao) cgLibProxy.getProxyInstance();
        cfLibDao.query("xrca");*/

        /*String str = new String("xrca");
        GeneralProxy generalProxy = new GeneralProxy(str);
        String strProxy = (String) generalProxy.getProxyInstance();
        System.out.println(strProxy.length());*/

        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(5);
        GeneralProxy generalProxy = new GeneralProxy(list);
        ArrayList listProxy = (ArrayList) generalProxy.getProxyInstance();
        System.out.println(listProxy.size());
        System.out.println(listProxy.get(1));
    }
}
