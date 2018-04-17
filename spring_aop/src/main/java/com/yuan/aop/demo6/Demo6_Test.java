package com.yuan.aop.demo6;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext4.xml")
public class Demo6_Test {

    @Resource(name = "customerDao")
    private CustomerDao customerDao;

    @Resource(name = "studentDao")
    private StudentDao studentDao;
    /*
    jdk的动态代理不支持类注入，只支持接口方式注入,
    因此这里不能将名为studentDao的StudentDaoImpl类注入到名为studentDaoImpl的StudentDaoImpl实现类
    */
//    @Resource(name="studentDao")
//    private StudentDaoImpl studentDaoImpl;

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
