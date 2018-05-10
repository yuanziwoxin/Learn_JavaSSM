package com.yuan.sm.global;

import javax.servlet.*;
import java.io.IOException;

/**
 * 编码过滤器
 */
public class EncodingFilter implements Filter {

    private String encoding="UTF-8";//默认为UTF-8编码格式

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //如果获取的初始化编码格式不为空(即配置了编码格式)，则将其赋值为encoding这个字段，否则encoding字段仍然取默认值
        if ((filterConfig.getInitParameter("ENCODING"))!=null)
          encoding=filterConfig.getInitParameter("ENCODING");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);//设置请求的编码格式
        servletResponse.setCharacterEncoding(encoding);//设置响应的编码格式
        filterChain.doFilter(servletRequest,servletResponse);//通过过滤器继续执行（放行）
    }

    @Override
    public void destroy() {
        encoding=null;
    }
}
