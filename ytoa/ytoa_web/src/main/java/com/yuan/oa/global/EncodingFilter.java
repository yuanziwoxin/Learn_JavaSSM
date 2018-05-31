package com.yuan.oa.global;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编码过滤器
 * 注意：这里实现的是javax.servlet.Filter这个接口
 */
public class EncodingFilter implements Filter {
    //设置默认编码格式
    private String defaultEncoding="utf-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //如果从web配置文件中的获取的编码格式不为空，则采用该编码格式；否则，采用默认的utf-8编码格式
        if (filterConfig.getInitParameter("encoding")!=null){
            defaultEncoding=filterConfig.getInitParameter("encoding");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        request.setCharacterEncoding(defaultEncoding);//设置请求的编码格式
        response.setCharacterEncoding(defaultEncoding);//设置响应的编码格式
        filterChain.doFilter(request,response);//放行
    }

    @Override
    public void destroy() {

    }
}
