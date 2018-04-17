package com.yuan.ioc.demo2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class demo2Test {

    /**
     * 测试 @PostContract 和 @PreDestroy的作用
     */
    @Test
    public void demo1(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Bean1 bean1=(Bean1) applicationContext.getBean("bean1");

        bean1.say();

        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

    @Test
    public void demo2(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Bean2 bean1=(Bean2) applicationContext.getBean("bean2");
        Bean2 bean2=(Bean2) applicationContext.getBean("bean2");

        /*
        (1) 如果Bean2的Scope是默认的singleton(即单例模式)，则输出true，表示两个对象的地址相同；
        (2) 如果Bean2的Scope是prototype(即多例模式)，则输出false，表示两个对象的地址不一；
         */
        System.out.println(bean1==bean2);

        ((ClassPathXmlApplicationContext) applicationContext).close();
    }
}
