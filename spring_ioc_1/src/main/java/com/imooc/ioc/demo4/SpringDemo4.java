package com.imooc.ioc.demo4;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo4 {
    /**
     * 1.测试通过构造方法的属性注入
     */
    @Test
    public void demo1(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        User user= (User) applicationContext.getBean("user");

        System.out.println(user);//输出属性注入后的属性的值
    }

    /**
     * 测试 ：1.通过setter方法的属性注入
     *       2.通过p命名空间进行属性注入
     */
    @Test
    public void demo2(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Person person= (Person) applicationContext.getBean("person");

        System.out.println(person);//输出属性注入后的属性的值
    }

    /**
     * 测试 ：4.通过SpEL进行属性注入
     *
     */
    @Test
    public void demo3(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Product product= (Product) applicationContext.getBean("product");

        System.out.println(product);//输出属性注入后的属性的值
    }

    /**
     * 测试 ：复杂类型的属性注入
     *
     */
    @Test
    public void demo4(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        CollectionBean collectionBean= (CollectionBean) applicationContext.getBean("collectionBean");

        System.out.println(collectionBean);//输出属性注入后的属性的值
    }

}
