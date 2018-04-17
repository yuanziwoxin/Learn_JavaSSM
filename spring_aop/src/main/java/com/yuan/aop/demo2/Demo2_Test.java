package com.yuan.aop.demo2;

import org.junit.Test;

public class Demo2_Test {
    /**
     * 测试CGLIB动态代理方法
     */
    @Test
    public void demo1(){
        ProductDao productDao=new ProductDao();

        //注意：productDao是目标类，而proxy是代理对象
        //创建一个CGLIB动态代理对象
        ProductDao proxy= (ProductDao) new MyCglibProxy(productDao).createProxy();

        proxy.save();
        proxy.update();
        proxy.delete();
        proxy.find();
    }
}
