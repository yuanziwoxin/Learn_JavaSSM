package com.yuan.aop.demo3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo3_Test {
    //属性注入：这里注入原对象
    //@Resource(name = "studentDao")

    //属性注入：这里注入代理对象
    @Resource(name = "studentDaoProxy")
    private StudentDao studentDao;

    /**
     * 测试 Advisor切面案例和AOP增强类型之前置通知
     */
    @Test
    public void demo1(){
        studentDao.save();
        studentDao.update();
        studentDao.delete();
        studentDao.find();
    }
}
