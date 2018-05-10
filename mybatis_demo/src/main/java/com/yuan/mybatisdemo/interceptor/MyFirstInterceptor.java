package com.yuan.mybatisdemo.interceptor;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

/**
 * 插件签名：用来告诉mybatis当前插件用来拦截哪个对象的哪个方法
 * 可以有多个插件签名，因此可以同时拦截多个方法
 * type:表示要拦截的接口，即要拦截哪个对象
 * method：拦截的方法
 * args:拦截的对象中的方法中的参数
 * 注意：这里配置的要拦截的对象的方法是需要会返回resultset的方法，不然不会执行intercept方法进行拦截
 */
@Intercepts({
        @Signature(type = ResultSetHandler.class,method ="handleResultSets",args = Statement.class )
})
public class MyFirstInterceptor implements Interceptor {
    //拦截目标对象的目标方法
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        System.out.println("First intercept 拦截的目标对象："+invocation.getTarget());
        Object object=invocation.proceed();//执行具体的目标方法
        return object;
    }
    //包装目标对象，为目标对象创建代理对象
    @Override
    public Object plugin(Object o) {
        System.out.println("First 将要包装的对象："+o);
        //利用Plugin中的wrap方法包装目标对象，并用当前类作为代理类
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("First 插件配置的初始化参数："+properties);
    }
}
