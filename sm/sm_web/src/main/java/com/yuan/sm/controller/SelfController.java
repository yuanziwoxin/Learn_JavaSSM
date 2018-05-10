package com.yuan.sm.controller;

import com.yuan.sm.entity.Staff;
import com.yuan.sm.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller("selfController")
public class SelfController {

    @Autowired
    private SelfService selfService;

    /**
     * 转发到登录页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     *
     *  /toLogin.do        login.jsp
     *
     */
    public void toLogin(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //获取来自用户输入的账户和密码
        String account=request.getParameter("account");
        String password=request.getParameter("password");

        //将用户输入的账户和密码与数据库中的进行比较
        Staff staff=selfService.login(account,password);

        if(staff==null){ //如果登录失败，则重定向到登录页面
            response.sendRedirect("toLogin.do");
        }
        else{
            //获取session
            HttpSession session=request.getSession();
            //在整个session中写入登录的用户对象
            session.setAttribute("USER",staff);
            //重定向到主页面
            response.sendRedirect("main.do");
        }
    }


    /**
     * 用户退出登录
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void loginout(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

            //获取session
            HttpSession session=request.getSession();
            //清除session中的用户对象
            session.setAttribute("USER",null);
            //重定向到登录页面
            response.sendRedirect("toLogin.do");

    }

    /**
     * 转发到用户主界面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     *
     *  /main.do
     */
    public void main(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    /**
     * 转发到个人信息页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     *
     *  /self/info.do
     *  因为这里是二级目录，完整目录为（根目录）/self/info.do
     *  我们这里访问的是根目录下的jsp页面，其完整目录为 （根目录）/info.jsp
     *  当前目录是在info.do,所以使用相对路径的方式info.jsp的路径可以表示为：
     *  ../info.jsp
     *
     */
    public void info(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../info.jsp").forward(request,response);
    }

    /**
     * 转发到修改密码页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     *
     * /self/toChangepassword.do
     *
     */
    public void toChangepassword(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../change_password.jsp").forward(request,response);
    }

    /**
     * 修改密码
     * @param request
     * @param response
     * @throws IOException
     *
     * /self/changePassword.do
     *
     */
    public void changePassword(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String password=request.getParameter("password");
        String password1=request.getParameter("password1");

        HttpSession session=request.getSession();
        Staff staff= (Staff) session.getAttribute("USER");

        if(staff.getPassword().equals(password)){
            selfService.changePassword(staff.getId(),password1);//修改密码
            /*
            使用一句javascript代码使得在父窗口下转到“../loginout.do”这个路径，因为这个页面有三个子页面；
            这是一句客户端代码，要写到客户端去，所以要使用response.getWriter().print()将其写到客户端去
             */
            response.getWriter().print("<script type=\"text/javascript\">parent.location.href=\"../loginout.do\"</script>");
        }else{
            response.sendRedirect("toChangepassword.do");
        }
    }
}
