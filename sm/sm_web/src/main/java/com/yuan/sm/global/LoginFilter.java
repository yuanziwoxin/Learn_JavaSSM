package com.yuan.sm.global;

import com.yuan.sm.entity.Staff;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 有些URL请求是登录之后才能访问，因此可以通过过滤器的形式限制URL请求的访问
 */

public class LoginFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;


        String path=request.getServletPath();
        //如果路径中含有login，则不用过滤，直接放行
        if(path.toLowerCase().indexOf("login")!=-1){
            filterChain.doFilter(request,response);
        }else{
            HttpSession session=request.getSession();
            Object obj=session.getAttribute("USER");//从session中获取用户对象
            //若用户已登录，则放行
            if (obj!=null){
                filterChain.doFilter(request,response);
            }else { //若未登录
                //注意：使用绝对路径（request.getContextPath()+"/toLogin.do"）
                response.sendRedirect(request.getContextPath()+"/toLogin.do");
            }
        }

    }

    @Override
    public void destroy() {

    }
}
