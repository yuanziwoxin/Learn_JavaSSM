package com.yuan.aop.demo1;

import org.junit.Test;

public class Demo1_Test {
      /**
     * 为未使用代理类
     */
    @Test
    public void demo1(){


        UserDao userDao = new UserDaoImpl();

        userDao.save();
        userDao.update();
        userDao.delete();
        userDao.find();
    }

    /**
     * 使用jdk代理类
     */
    @Test
    public void demo2(){

        UserDao userDao=new UserDaoImpl();

        //产生一个代理类对象
        UserDao proxy= (UserDao) new MyJdkProxy(userDao).createProxy();

        //调用增强的（代理的）方法
        proxy.save();
        proxy.update();
        proxy.delete();
        proxy.find();
    }

}
