package com.yuan.pagehelper.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuan.pagehelper.entity.Person;
import com.yuan.pagehelper.service.Impl.PersonServiceImpl;
import com.yuan.pagehelper.service.Impl.UserServiceImpl;
import com.yuan.pagehelper.service.PersonService;
import com.yuan.pagehelper.service.UserService;
import com.yuan.pagehelper.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet/PersonServlet")
public class PersonServlet extends HttpServlet {

    private PersonService personService=new PersonServiceImpl();

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
        /*
        使用PageHelper实现分页（相对与传统分页方式，代码得到很大简化）
         */
        //PageHepler的startPage方法得到当前页面的一些信息（注意Page<?>表示泛型）
        Page<?> pages=PageHelper.startPage(Integer.parseInt(num),5);
        //查询所有人员的信息，并以Person类的List集合方式进行返回
        List<Person> persons=personService.getAllPersonsByPageHelper();
        //获取分页的相关信息，封装在PageInfo类中
        PageInfo<?> pageInfo=pages.toPageInfo();

        request.setAttribute("persons",persons);//把查询到的人员信息结果集放到request域中

        request.setAttribute("pageInfo",pageInfo);//把分页信息放到request域中

        request.getRequestDispatcher("/person.jsp").forward(request,response);

    }
}
