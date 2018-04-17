package com.yuan.aop.demo5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext3.xml")
public class Demo5_Test {

    @Resource(name = "customerDao")
    private CustomerDao customerDao;

    //注意：这里使用的是将studentDao的实现类注入到StudentDao的接口吗？
    @Resource(name = "studentDao")
    private StudentDao studentDao;

    @Test
    public void demo1(){

        customerDao.save();
        customerDao.update();
        customerDao.delete();
        customerDao.find();

        studentDao.save();
        studentDao.update();
        studentDao.delete();
        studentDao.find();

    }

}
