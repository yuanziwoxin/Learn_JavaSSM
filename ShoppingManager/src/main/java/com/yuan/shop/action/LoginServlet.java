package com.yuan.shop.action;

import com.yuan.shop.bean.User;
import com.yuan.shop.service.ShopService;
import com.yuan.shop.utils.Constants;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;

    //定义业务层对象
    private ShopService shopService = null;

    //在Servlet启用之前会掉用这个方法
    @Override
    public void init() throws ServletException {
        super.init();
        //在这个方法中获取Spring容器，然后从容器中得到业务层对象
        ServletContext servletContext=this.getServletContext();//首先获得ServletContext上下文对象
        //获取一个Web目录的上下文对象，这其实就是一个Spring容器对象
        WebApplicationContext webApplicationContext=WebApplicationContextUtils.getWebApplicationContext(servletContext);
        //通过Spring容器得到这个bean（对象），即这个业务层对象
        shopService= (ShopService) webApplicationContext.getBean("shopService");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.request=req;
        this.response=resp;

        String method=request.getParameter("method");
        switch (method){
            case "getJsp":
                //转到登录界面
                req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
                break;
            case "login":
                login();
                break;
        }

    }

    private void login() {

        String loginName=request.getParameter("loginName");
        String password=request.getParameter("passWord");

        try{

            Map<String,Object> results=shopService.login(loginName,password);
            if ((int)results.get("code")==1) //登录名和密码匹配正确
            {

                //HttpSession session=request.getSession();
                User user= (User) results.get("msg");
                request.setAttribute(Constants.USER_SESSION,user);
                response.sendRedirect(request.getContextPath()+"/list?method=getAll");
            }
            else //如果登录失败
            {
                String msg=results.get("msg")+"";
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
            }

        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
