package com.yuan.aspectj.demo2;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 切面类
 */
public class MyAspectjXml {

    /**
     * 前置通知
     */
    public void before(){
        System.out.println("XML方式的前置通知....");
    }

    /**
     * 后置通知
     */
    public void afterReturning(Object result){
        System.out.println("XML方式的后置通知...."+result);
    }

    /**
     * 环绕通知
     */
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("XML方式的环绕前通知....");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("XML方式的环绕后通知....");
        return proceed;
    }

    /**
     * 异常抛出通知
     */
    public void afterThrowing(Throwable error){
        System.out.println("XML方式的异常抛出通知...."+error);
    }

    /**
     * 最终通知
     */
    public void after(){
        System.out.println("XML方式的最终通知......");
    }
}
