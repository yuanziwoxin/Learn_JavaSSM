package com.imooc.ioc.demo3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第五步：初始化前执行的方法...");
        return bean;
    }

    /**
     *   这里可以用于在执行一些业务逻辑之前进行一些校检程序
     */
    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        System.out.println("第八步：初始化后执行的方法...");
        if ("userDao".equals(beanName))//如果这个类是userDao，则对其中的某些（或全部）方法进行增强
        {
            Object proxy=Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                //invoke表示调用的意思
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if ("save".equals(method.getName()))//在save方法（增加用户方法）之前增加校检程序
                    {
                        System.out.println("=======执行权限校检程序=======");
                        return method.invoke(bean,args);//校检完就直接调用该save程序（即增加用户方法）；
                    }
                    return method.invoke(bean,args);//如果不是save方法，则不做权限校检，直接调用该方法即可
                }
            });
            return proxy;//增强就会返回代理
        }
        else {

            return bean;//不增强就直接返回bean对象
        }
    }
}
