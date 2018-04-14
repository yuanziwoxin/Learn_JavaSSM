package com.imooc.ioc.demo3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Man implements BeanNameAware,BeanFactoryAware,ApplicationContextAware,InitializingBean,DisposableBean{

    private String name;
    //设置属性
    public void setName(String name) {
        System.out.println("第二步：设置属性...."+name);
        this.name = name;
    }
    //构造函数
    public Man(){
        System.out.println("第一步：初始化...  Man被实例化了...");
    }
    //初始化方法
    public void setup(){
        System.out.println("第七步：Man被初始化了...");
    }
    //销毁方法
    public void teardown(){
        System.out.println("第十一步：Man被销毁了（执行自定义的销毁方法）...");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("第三步：设置Bean的名称...."+s);//即applicationContext中bean节点中的id值
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("第四步：设置工厂.....");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("或第四步：设置上下文对象（了解工厂信息）...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("第六步：属性设置后执行....");
    }

    public void run(){
        System.out.println("第九步：执行业务处理");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("第十步：执行Spring销毁方法");
    }
}
