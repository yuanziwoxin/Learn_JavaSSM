package com.yuan.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    //将application.yml文件中自定义的配置内容依次注入到下列字段中
    @Value("${shopping-mall.config.name}")
    private String name;
    @Value("${shopping-mall.config.description}")
    private String description;
    @Value("${shopping-mall.config.hot-sales}")
    private Integer hotSales;
    @Value("${shopping-mall.config.show-advertise}")
    private Boolean showAdvertise;


    @RequestMapping(value = "/out")
    @ResponseBody
    public String out()
    {
        return "Spring Initializr:Successs!";
    }

    //将application.yml中自定义的配置内容显示出来
    @RequestMapping(value = "/info")
    @ResponseBody
    public String info()
    {
        return String.format("name:%s  description:%s  hot-sales: %s  show-advertise: %s",
                name,description,hotSales,showAdvertise);
    }
}
