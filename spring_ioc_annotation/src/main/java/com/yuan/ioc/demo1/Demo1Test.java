package com.yuan.ioc.demo1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo1Test {

    /**
     * 使用Component注解定义Bean
     */
    @Test
    public void demo1(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        UserService userService=(UserService) applicationContext.getBean("userService");

        String username=userService.sayHello("张三丰");

        System.out.println(username);
    }

    /**
     * 注解方式
     */
    @Test
    public void demo2(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        UserService userService=(UserService) applicationContext.getBean("userService");

        userService.eat();
        userService.save();
    }

}
