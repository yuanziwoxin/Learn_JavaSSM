package com.yuan.aspectj.demo1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;


/**
 * 切面类
 */
@Aspect  //定义为切面类
public class MyAspectAnnotation {

    /**
     * 前置通知
     * @param joinPoint
     */
    //@Before(value = "execution(* com.yuan.aspectj.demo1.ProductDao.delete(..))")
    @Before(value = "myPointcut3()")
    //可以传入JoinPoint对象，从而获取其切点信息
    public void before(JoinPoint joinPoint){
        //输出的结果为=====前置通知=====execution(void com.yuan.aspectj.demo1.ProductDao.delete())
        System.out.println("=====前置通知====="+joinPoint);
    }

    /**
     * 后置通知
     * @param result
     */
    //通过returning属性可以定义方法的返回值作为参数，从而获取方法的返回值
    //@AfterReturning(value = "execution(* com.yuan.aspectj.demo1.ProductDao.update(..))",returning = "result")
    //通知多个切点，可以使用“||”进行连接
    @AfterReturning(value = "myPointcut2() || myPointcut1()",returning = "result")
    public void afterReturning(Object result){//注意:这里的对象名（result）必须与returning属性（result）的值相同
        System.out.println("====后置通知===="+result);//这里获取到了update方法的返回值
    }

    /**
     * 环绕通知
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    //@Around(value = "execution(* com.yuan.aspectj.demo1.ProductDao.findall(..))")
    @Around(value = "myPointcut5()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("====环绕前通知====");

        //若不使用ProceedingJoinPoint中的proceed方法，则目标方法就会被拦截（就不会被执行），这里即findall不会被执行
        Object proceed = proceedingJoinPoint.proceed();

        System.out.println("====环绕后通知====");

        return proceed;
    }

    /**
     * 异常抛出通知
     * 通过设置throwing属性，可以设置发生异常对象参数；
     * @param error
     */
    //@AfterThrowing(value = "execution(* com.yuan.aspectj.demo1.ProductDao.findone(..))",throwing = "error")
    @AfterThrowing(value = "myPointcut4()",throwing = "error")
    public void throwale(Throwable error){
        System.out.println("=====异常抛出通知====="+error);//输出完整异常信息error：java.lang.ArithmeticException: / by zero
        System.out.println("=====异常抛出通知====="+error.getMessage());//输出异常信息：/ by zero
    }

    /**
     * 最终通知：不管怎么样都会执行（即使有异常也会执行）
     */
    //@After(value = "execution(* com.yuan.aspectj.demo1.ProductDao.save(..))")
    @After(value = "myPointcut1()")
    public void after(){
        System.out.println("=====最终通知=====");
    }

    /**
     * 通过@Pointcut为切点命名
     * （1）这样可以避免每个通知内定义切点，从而更易维护；
     * （2）切点方法：private void 无参数方法，方法名为切点名；
     * （3）当通知多个切点时，可以使用“||”进行连接
     */
    @Pointcut(value = "execution(* com.yuan.aspectj.demo1.ProductDao.save(..))")
    private void myPointcut1(){

    }


    @Pointcut(value = "execution(* com.yuan.aspectj.demo1.ProductDao.update(..))")
    private void myPointcut2(){

    }

    @Pointcut(value = "execution(* com.yuan.aspectj.demo1.ProductDao.delete(..))")
    private void myPointcut3(){

    }

    @Pointcut(value = "execution(* com.yuan.aspectj.demo1.ProductDao.findone(..))")
    private void myPointcut4(){

    }

    @Pointcut(value = "execution(* com.yuan.aspectj.demo1.ProductDao.findall(..))")
    private void myPointcut5(){

    }

}
