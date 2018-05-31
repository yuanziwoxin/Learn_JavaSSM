package com.yuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    //当用户发送"**/index"请求
    @RequestMapping(value = "/index")
    public String index()
    {
        /*
        经过SpringMVC-servlet.xml配置的视图解析器的解析，添加前后缀，
        返回到/WEB-INF/pages/index.jsp页面
         */
        return "index";
    }
}
