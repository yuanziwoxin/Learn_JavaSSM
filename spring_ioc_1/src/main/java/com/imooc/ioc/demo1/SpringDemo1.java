package com.imooc.ioc.demo1;


import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class SpringDemo1 {

    @Test
    /**
    (1)传统开发方式
    */
    public void demo1(){
      UserService userService=new UserServiceImpl();
      userService.sayHello();
    }

    @Test
    /**
    (2)Spring开发方式
     */
    public void demo2(){
        //创建Spring工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类（通过userService获取实例对象UserServiceImpl）
        UserService userService=(UserService) applicationContext.getBean("userService");
        userService.sayHello();
    }
    @Test
    /**
    (3)读取系统磁盘文件
     */
    public void demo3(){
        //创建Spring的工厂
        ApplicationContext applicationContext=new FileSystemXmlApplicationContext("e:\\applicationContext.xml");
        //通过工厂获得类
        UserService userService=(UserService) applicationContext.getBean("userService");
        //通过类调用类中的方法
        userService.sayHello();
    }
    @Test
    /**
     * (4)传统方式的工厂类：BeanFactory
     */
    public void demo4(){
        //创建工厂类(XmlBeanFactory方法已经过期)
        BeanFactory beanFactory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        /*
        //创建工厂类（这里用于读取磁盘文件）
        BeanFactory beanFactory=new XmlBeanFactory(new FileSystemResource("e:\\applicationContext.xml"));
         */
        //通过工厂获得类
        UserService userService= (UserService) beanFactory.getBean("userService");

        userService.sayHello();
    }

}
