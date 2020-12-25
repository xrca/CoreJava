package com.xrca.javaproxy.proxy;

import com.xrca.javaproxy.dao.UserDao;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xrca
 * @description 用户类代理，通过聚合完成代理
 * @date 2020-12-25 12:14
 **/
@AllArgsConstructor
public class LogUserProxy2 implements UserDao {
    private UserDao userDao;

    @Override
    public void query(String name) {
        System.out.println("log proxy2...");
        userDao.query(name);
    }
}
