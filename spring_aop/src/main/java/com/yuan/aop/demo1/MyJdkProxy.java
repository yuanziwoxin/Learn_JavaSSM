package com.yuan.aop.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyJdkProxy implements InvocationHandler {

    private UserDao userDao;

    //在构造方法中把userDao传进来
    public MyJdkProxy(UserDao userDao){
        this.userDao=userDao;
    }
    //利用createProxy方法创建一个代理对象
    public Object createProxy(){
        Object proxy=Proxy.newProxyInstance(userDao.getClass().getClassLoader(),userDao.getClass().getInterfaces(),this);
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //当要执行的方法是save方法时，在save方法之前执行权限校验方法
        if ("save".equals(method.getName())){
            System.out.println("======权限校验======");
            return method.invoke(userDao,args);
        }
        return method.invoke(userDao,args);
    }
}
