package com.yuan.aspectj.demo1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo1_Test {

    @Resource(name = "productDao")
    private ProductDao productDao;

    /**
     * 测试通过AspectJ注解实现各种类型的通知（前置、后置、环绕、异常抛出、最终）
     */
    @Test
    public void demo1(){

        productDao.update();
        productDao.delete();
        productDao.findall();
        productDao.findone();
        productDao.save();
    }
}
