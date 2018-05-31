package com.yuan.oa.controller;

import com.yuan.oa.biz.GlobalBiz;
import com.yuan.oa.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller("globalController")
public class GlobalController {

    @Resource ( name = "globalBiz" )
    private GlobalBiz globalBiz;

    @RequestMapping ( "/to_login" )
    public String toLogin() {
        return "login";//跳转到登录界面
    }

    @RequestMapping ( "/login" )
    public String login(HttpSession session, @RequestParam String ename, @RequestParam String password) {
        Employee employee = globalBiz.login(ename, password);
        if (employee != null)//登录成功
        {
            session.setAttribute("employee", employee);//将该用户对象传递至session域中
            return "redirect:self";//跳转到个人信息页面
        } else {
            return "redirect:to_login";
        }
    }

    @RequestMapping("/self")
    public String self()
    {
        return "self";//跳转到个人信息页面
    }

    @RequestMapping("/quit")
    public String quit(HttpSession session) //注销
    {
        session.setAttribute("employee",null);//将Session域中的employee对象设置为空
        return "redirect:to_login";//跳转到登录页面
    }

    @RequestMapping("/to_change_password")
    public String toChangepassword()
    {
        return "change_password";
    }

    @RequestMapping("/change_password")
    public String changePassword(HttpSession session,@RequestParam String oldPassword,
                                 @RequestParam String newPassword1,@RequestParam String newPassword2)
    {
        //从Session域中获取用户对象
        Employee employee= (Employee) session.getAttribute("employee");
        //如果输入的原密码正确
        if (employee.getPassword().equals(oldPassword))
        {
            //如果两次输入的新密码相同
            if (newPassword1.equals(newPassword2))
            {
                employee.setPassword(newPassword1);//设置新密码
                globalBiz.changePassword(employee);//修改密码
                session.setAttribute("employee",employee);
                return "redirect:self";//修改成功后跳转到个人信息页面
            }

        }
        return "redirect:to_change_password";
    }
}
