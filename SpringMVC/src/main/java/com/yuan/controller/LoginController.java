package com.yuan.controller;

import com.yuan.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    //用户登录
    @RequestMapping(value = "/login")
    public String login()
    {
        return "login";//路径为：/WEB-INF/pages/login.jsp
    }

    //处理用户登录信息
    @RequestMapping(value = "/logined")
    public String logined(@RequestParam("account") String account,
                          @RequestParam("password") String password,
                          HttpSession session)
    {
        if (account.equals("admin") && password.equals("123"))
        {
            User user=new User();
            user.setAccount(account);
            user.setPassword(password);
            session.setAttribute("session_user",user);
            return "redirect:user/search";
        }
        else
        {
            return "redirect:login";//返回到登录页面
        }
    }
}
