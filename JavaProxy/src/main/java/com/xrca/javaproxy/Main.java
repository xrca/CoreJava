package com.xrca.javaproxy;

import com.xrca.javaproxy.dao.UserDao;
import com.xrca.javaproxy.dao.impl.UserDaoImpl;
import com.xrca.javaproxy.proxy.LogUserProxy;
import com.xrca.javaproxy.proxy.LogUserProxy2;
import com.xrca.javaproxy.util.ProxyUtil;

/**
 * @author xrca
 * @description TODO
 * @date 2020-12-25 12:15
 **/
public class Main {
    public static void main(String[] args) {
        UserDaoImpl userDao = new LogUserProxy();
        userDao.query("xrca");

        UserDao userDaoImpl = new UserDaoImpl();
        UserDao proxy = new LogUserProxy2(userDaoImpl);
        proxy.query("xrca");

        UserDao userDaoProxy = (UserDao) ProxyUtil.newProxyInstance(userDaoImpl);
        userDaoProxy.query("xrca");
    }
}
