package com.yuan.aop.demo2;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//注意这里实现的是org.springframework.cglib.proxy这个包下的MethodInterceptor类
public class MyCglibProxy implements MethodInterceptor {

    private ProductDao productDao;

    public MyCglibProxy(ProductDao productDao){
        this.productDao=productDao;
    }

    //创建一个CGLIB动态代理
    public Object createProxy(){
        //1.创建一个CGLIB核心类
        Enhancer enhancer=new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(productDao.getClass());//productDao是目标类，要对这个目标类产生一个子类
        //3.设置回调
        enhancer.setCallback(this);
        //4.生成代理
        Object proxy=enhancer.create();
        return proxy;

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if ("save".equals(method.getName())){
            System.out.println("====权限校验====");
            return methodProxy.invokeSuper(o,objects);//invokeSuper表示调用父类的方法
        }
        return methodProxy.invokeSuper(o,objects);
    }
}
