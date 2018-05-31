package com.yuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    //查找用户
    @RequestMapping(value = "/search")
    public String search()
    {
        System.out.println("2. ----UserController: search----");
        return "user/search";// 表示/WEB-INF/pages/user/search.jsp
    }

    //修改用户密码
    @RequestMapping(value = "/changePsw")
    public String changePsw()
    {
        System.out.println("2. ----UserController: changePsw----");
        return "user/changePsw";// 表示/WEB-INF/pages/user/changePsw.jsp
    }

    //修改用户头像
    @RequestMapping(value = "/changeHeadPic")
    public String changeHeadPic()
    {
        System.out.println("2. ----UserController: changeHeadPic----");
        return "user/changeHeadPic";// 表示/WEB-INF/pages/user/changeHeadPic.jsp
    }

    //修改用户背景
    @RequestMapping(value = "/changeBackground/{id}")
    public String changeBackground(HttpSession session)
    {
        System.out.println("2. ----UserController: changeBackground----");
        return "user/changeBackground";
    }

}
