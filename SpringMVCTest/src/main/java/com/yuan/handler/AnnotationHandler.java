package com.yuan.handler;

import com.yuan.entity.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class AnnotationHandler {

    /**
     * 通过ModelAndView完成数据的传递，视图的解析
     * @return
     */
    @RequestMapping("/modelAndViewTest")
    public ModelAndView modelAndViewTest()
    {
        //加载模型数据和逻辑视图
        ModelAndView modelAndView=new ModelAndView();
        //添加模型数据
        modelAndView.addObject("name","jack");
        //添加逻辑视图
        modelAndView.setViewName("show");//设置逻辑视图的名字
        return modelAndView;
    }

    /**
     * 业务方法：通过Model完成数据的传递，String进行视图的解析
     * @param model
     * @return
     */
    @RequestMapping("/modelTest")
    public String modelTest(Model model)
    {
        model.addAttribute("name","kevin");
        return "show";
    }

    /**
     * 通过Map完成数据的传递，String进行视图的解析
     * @param map
     * @return
     */
    @RequestMapping("/mapTest")
    public String mapTest(Map<String,String> map)
    {
        map.put("name","steven");
        return "show";
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @RequestMapping("/addGoods")
    public ModelAndView addGoods(Goods goods)
    {
        System.out.println("商品名称："+goods.getName());
        System.out.println("商品价格："+goods.getPrice());
        //加载模型数据和逻辑视图
        ModelAndView modelAndView=new ModelAndView();
        //设置模型数据
        modelAndView.addObject("goods",goods);
        //设置逻辑视图
        modelAndView.setViewName("show");
        return modelAndView;
    }
}
