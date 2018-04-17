package com.yuan.aop.demo4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
//指定使用的单元测试执行类，这里指定的是SpringJUnit4ClassRunner.class
@RunWith(SpringJUnit4ClassRunner.class)
//指定spring配置文件的所在路径，可同时指定多个文件，文件路径之间用逗号隔开；
@ContextConfiguration("classpath:applicationContext2.xml")
public class Demo4_Test {
    //使用原对象（目标类）
    //@Resource(name = "customerDao")

    //使用代理对象（这里注入的属性值是一个代理对象）
    @Resource(name = "customerDaoProxy")
    private CustomerDao customerDao;

    @Test
    public void demo1(){

        customerDao.save();
        customerDao.update();
        customerDao.delete();
        customerDao.find();

    }
}
