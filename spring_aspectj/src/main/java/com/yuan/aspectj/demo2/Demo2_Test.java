package com.yuan.aspectj.demo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class Demo2_Test {

    @Resource(name = "customerDao")
    private CustomerDao customerDao;

    /**
     * 测试：通过XML方式配置切面
     */
    @Test
    public void demo1(){

        customerDao.save();
        customerDao.update();
        customerDao.delete();
        customerDao.findone();
        customerDao.findall();

    }
}
