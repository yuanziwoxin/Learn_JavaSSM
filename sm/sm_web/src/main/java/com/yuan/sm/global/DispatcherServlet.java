package com.yuan.sm.global;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DispatcherServlet extends GenericServlet {

    private ApplicationContext context;

    /*
    把“context=new ClassPathXmlApplicationContext("spring.xml")”放在Servlet初始化方法中，
    这样只需要在初始化的时候读取一次配置文件即可，而不用每次执行service方法时都读取一次配置文件
     */
    public void init() throws ServletException {
        super.init();
        //要想访问IOC容器，首先要获取上下文
        context=new ClassPathXmlApplicationContext("spring.xml");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //ServletRequest中没有获得请求路径的方法，所以强转为HttpServletRequest
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        /*
         * 模版
         * /staff/add.do    /login.do
         * staffController(staff控制器的名称)
         * public void add(HttpServletRequest request,HttpServletResponse response){}
         *
         */
        /*
          假设request.getServletPath()获取到的路径是/staff/add.do
         */
        String path=request.getServletPath().substring(1);//把路径中的根路径符号“\”截去，即所得的是staff/add.do

        String beanName=null;
        String methodName=null;

        int index=path.indexOf("/");//获取“/”的位置
        if (index!=-1){
            /*
            从上面所得路径（staff/add.do）继续截取，所截取的结果为staff
             */
            beanName=path.substring(0,index)+"Controller";//staffController
            methodName=path.substring(index+1,path.indexOf(".do"));//截取后的结果为add
        }
        else{
            beanName="selfController";//路径中没有对象，则指定对象名为selfController
            methodName=path.substring(0,path.indexOf(".do"));
        }


        Object object=context.getBean(beanName);
        try {
            //通过类的getMethod方法来反射获取Method对象
            Method method=object.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(object,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
