package com.imooc.ioc.demo2;

/**
 * Bean2的静态工厂
 */
public class Bean2Factory {
    //与实例工厂方法实例Bean的方式的主要差别就是：这里的static
    public static Bean2 createBean2(){ //这里的名字可以随便取（这里取的是createBean2）
        System.out.println("Bean2被实例化了....");
        return new Bean2();//返回第二个实例
    }
}
