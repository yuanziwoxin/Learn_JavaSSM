package com.yuan.pagehelper.web;

import com.yuan.pagehelper.service.Impl.UserServiceImpl;
import com.yuan.pagehelper.service.UserService;
import com.yuan.pagehelper.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/UserServlet")
public class UserServlet extends HttpServlet {

    private UserService userService=new UserServiceImpl();

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String method=request.getParameter("method");

       if (method.equals("all"))//如果方式是all的话，则查询所有信息
       {
            selectAllUsers(request,response);//查询所有用户信息
       }
   }

   //获取所有的用户信息
    public void selectAllUsers(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String num=request.getParameter("num");//获取当前要访问的页码

        if (null==num)//如果num为空,第一次访问的话，就设置为第1页
        {
            num="1";
        }
        PageUtil page=null;

        try {
            page=userService.getAllUsers(num);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("page",page);//将查询到的PageUtil对象封装到request域中

        request.getRequestDispatcher("/user.jsp").forward(request,response);//转发到user.jsp页面


    }
}
