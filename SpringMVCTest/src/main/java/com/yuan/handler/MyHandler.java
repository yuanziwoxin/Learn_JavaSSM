package com.yuan.handler;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandler implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        //装载模型数据和逻辑视图
        ModelAndView modelAndView=new ModelAndView();
        //添加模型数据
        modelAndView.addObject("name","yuan");
        //添加逻辑视图（通过springmvc中配置的视图解析器可以将逻辑视图转化为物理视图,"/show.jsp"（即web层的show.jsp））
        modelAndView.setViewName("show");//设置视图（希望将“yuan”这个名字传到“show”这个视图当中）
        return modelAndView;
    }
}
