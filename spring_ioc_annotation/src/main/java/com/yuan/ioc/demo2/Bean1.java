package com.yuan.ioc.demo2;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("bean1")
public class Bean1 {

    /**
     * 在bean对象初始化的时候执行init方法（方法的名字可以任意取）
     */
    @PostConstruct
    public void init(){
        System.out.println("init Bean....");
    }

    public void say(){
        System.out.println("Hello,Spring!");
    }
    /**
     * 在bean对象销毁的时候执行destory方法
     */
    @PreDestroy
    public void destory(){
        System.out.println("destory Bean...");
    }
}
