package com.yuan.sm.controller;

import com.yuan.sm.entity.Log;
import com.yuan.sm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("logController")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 显示操作日志
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     *
     *  /log/operationLoglist.do
     */
    public void operationLoglist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> list=logService.getOperationlog();
        request.setAttribute("LIST",list);//将list中的内容传递给request中，对应名为LIST的键
        request.setAttribute("TYPE","操作");//设置显示的日志类型
        request.getRequestDispatcher("../log_list.jsp").forward(request,response);
    }

    /**
     * 显示登录日志
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     *
     *  /log/loginLoglist.do
     */
    public void loginLoglist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> list=logService.getLoginlog();
        request.setAttribute("LIST",list);//将list中的内容传递给request中，对应名为LIST的键
        request.setAttribute("TYPE","登录");//设置显示的日志类型
        request.getRequestDispatcher("../log_list.jsp").forward(request,response);
    }

    /**
     * 显示系统日志
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     *
     *  /log/systemLoglist.do
     */
    public void systemLoglist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> list=logService.getSystemlog();
        request.setAttribute("LIST",list);//将list中的内容传递给request中，对应名为LIST的键
        request.setAttribute("TYPE","系统");//设置显示的日志类型
        request.getRequestDispatcher("../log_list.jsp").forward(request,response);
    }
}
