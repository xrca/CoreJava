package com.xrca.javaproxy.proxy;

import com.xrca.javaproxy.dao.impl.UserDaoImpl;

/**
 * @author xrca
 * @description 用户类代理，通过继承完成代理
 * @date 2020-12-25 12:14
 **/
public class LogUserProxy extends UserDaoImpl {

    @Override
    public void query(String name) {
        System.out.println("log proxy...");
        super.query(name);
    }
}
