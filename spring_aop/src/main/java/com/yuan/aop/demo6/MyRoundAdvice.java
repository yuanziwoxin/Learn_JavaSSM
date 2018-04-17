package com.yuan.aop.demo6;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

//注意这里实现的接口是org.aopalliance.intercept.MethodInterceptor，而不是org.springframework那个
public class MyRoundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        System.out.println("=====环绕的前置通知=====");

        //执行目标类的相应方法
        Object object=methodInvocation.proceed();

        System.out.println("=====环绕的后置通知=====");

        return object;
    }
}
