package com.imooc.ioc.demo3;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Springdemo3 {
    /**
     * 测试Bean的作用域
     */
    @Test
    public void demo1(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Person person1= (Person) applicationContext.getBean("person");
        Person person2= (Person) applicationContext.getBean("person");

        /**
         * 当Bean的作用域是默认的singleton时，
         * Bean只以单实例的方式存在，这里输出的结果是一样的
         */

        System.out.println(person1);
        System.out.println(person2);

    }

    /**
     * 测试Spring容器中Bean的生命周期：测试是否初始化
     */
    @Test
    public void demo2(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Man man= (Man) applicationContext.getBean("man");

        System.out.println(man);


    }

    /**
     * 测试Spring容器中Bean的生命周期：测试是否初始化和销毁
     */
    @Test
    public void demo3(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Man man1= (Man) applicationContext.getBean("man");

        /**
         * 注意：ApplicationContext是个接口，没有销毁方法，
         *      所以这里要用ClassPathXmlApplicationContext这个实现类（类中才有销毁方法）
         */
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

    /**
     * 测试Spring Bean的生命周期
     */
    @Test
    public void demo4(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        Man man2= (Man) applicationContext.getBean("man");

        man2.run();//调用处理业务的方法
        /**
         * 注意：ApplicationContext是个接口，没有销毁方法，
         *      所以这里要用ClassPathXmlApplicationContext这个实现类（类中才有销毁方法）
         */
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

    /**
     *  测试BeanPostProcessor的作用
     *  注意：BeanPostProcessor包含初始化前执行的方法和初始化后执行的方法，
     *      其中初始化后执行的方法可以用来实现一些业务逻辑之前的校检工作。
     */
    @Test
    public void demo5(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类
        UserDao userDao= (UserDao) applicationContext.getBean("userDao");

        userDao.findall();
        userDao.save();
        userDao.update();
        userDao.delete();

    }

}
