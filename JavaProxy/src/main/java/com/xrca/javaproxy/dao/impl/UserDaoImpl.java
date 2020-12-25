package com.xrca.javaproxy.dao.impl;

import com.xrca.javaproxy.dao.UserDao;

/**
 * @author xrca
 * @description
 * @date 2020-12-25 12:12
 **/
public class UserDaoImpl implements UserDao {
    public void query(String name) {
        System.out.println("query name = " + name);
    }
}
