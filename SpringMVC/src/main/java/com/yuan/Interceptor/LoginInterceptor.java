package com.yuan.Interceptor;

import com.yuan.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//登录拦截器（必须实现HandlerInterceptor接口）
public class LoginInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println("1. ----login: preHandle----");
        User user= (User) request.getSession().getAttribute("session_user");
        if (user==null)
        {
            response.sendRedirect(request.getContextPath()+"/login");
            return false;//如果没有登录，则终止所有的请求
        }
        //return false;//如果返回false的话，则会终止所有请求（无论是Controller里的方法和还是其他的拦截器方法都不会执行）
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("3. ----login: postHandle----");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("4. ----login: afterCompletion----");
    }
}
