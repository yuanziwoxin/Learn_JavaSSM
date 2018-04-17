package com.yuan.ioc.demo3;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo3_Test {

    @Test
    public void demo1(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        ProductService productService= (ProductService) applicationContext.getBean("productService");

        productService.save();
    }
}
