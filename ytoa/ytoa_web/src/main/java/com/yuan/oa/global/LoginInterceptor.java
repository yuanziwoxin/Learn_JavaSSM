package com.yuan.oa.global;

import com.yuan.oa.entity.Employee;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//登录拦截器（还要在spring-web.xml中进行拦截器的配置）
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
        假设请求地址为：http://localhost:8080/employee/list
        request.getRequestURI():获取的是相对请求地址，即/employee/list，其返回类型为String
        request.getRequestURL():获取的是绝对（完整）请求地址，即http://localhost:8080/employee/list，其返回类型为StringBuffer
         */
        //获取URI
        String url=request.getRequestURI();
        //将获取的路径先全部转化为小写的形式，然后判断其中是否有“login”字符，有则返回一个大于等于0的整数，否则返回一个负数；
        if (url.toLowerCase().indexOf("login")>=0)
        {
            return true;
        }

        HttpSession session=request.getSession();//获取Session对象
        Employee employee= (Employee) session.getAttribute("employee");
        if (employee!=null)//如果登录了,则放行
        {
            return true;
        }
        //如果未登录，则跳转至登录页面
        response.sendRedirect(request.getContextPath()+"/to_login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
