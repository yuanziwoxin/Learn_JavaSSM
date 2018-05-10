package com.yuan.mybatisdemo.interceptor;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

/**
 * 注意：当配置多个拦截器且这几个拦截器拦截的对象相同时，mybatis会遍历多个拦截器，
 * 并按照顺序来执行拦截器的plugin方法（包装目标对象方法），
 * 被拦截的对象会被层层地进行代理，被层层地包裹，
 * 而在执行目标方法（拦截对象的方法）时，其会一层一层地调用拦截器，
 * 是按照代理的逆向执行的，即先从最外面一层开始执行（
 * 先执行最外层拦截器的intercept方法，然后往里执行）。
 *
 * 这里两个拦截器的plugin方法执行顺序是：先执行MyFirstInterceptor中的plugin方法，然后执行MySecondInterceptor；
 * 而intercept方法的指向顺序则相反；
 *
 */
@Intercepts({
        @Signature(type = ResultSetHandler.class,method ="handleResultSets",args = Statement.class )
})
public class MySecondInterceptor implements Interceptor {
    //拦截目标对象的目标方法
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        System.out.println("Second intercept 拦截的目标对象："+invocation.getTarget());
        Object object=invocation.proceed();//执行具体的目标方法
        return object;
    }
    //包装目标对象，为目标对象创建代理对象
    @Override
    public Object plugin(Object o) {
        System.out.println("Second 将要包装的对象："+o);
        //利用Plugin中的wrap方法包装目标对象，并用当前类作为代理类
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {

        System.out.println("Second 插件配置的初始化参数："+properties);
    }
}
