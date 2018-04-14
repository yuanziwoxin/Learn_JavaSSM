package com.imooc.ioc.demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanTest {
    /**
     * 测试三种实例化Bean的方法之：使用无参数构造器的方式
     */
    @Test
    public void demo1(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Bean1 bean1= (Bean1) applicationContext.getBean("bean1");
        //在获得类的时候就执行了该类的构造函数

    }

    /**
     * 测试三种实例化Bean的方法之：使用静态工厂方法的方式
     */
    @Test
    public void demo2(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Bean2 bean2= (Bean2) applicationContext.getBean("bean2");

    }

    /**
     * 测试三种实例化Bean的方法之：使用实例工厂方法的方式
     */
    @Test
    public void demo3(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Bean3 bean3= (Bean3) applicationContext.getBean("bean3");

    }
}
