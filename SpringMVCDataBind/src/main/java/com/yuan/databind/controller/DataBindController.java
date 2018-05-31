package com.yuan.databind.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.yuan.databind.dao.CourseDao;
import com.yuan.databind.entity.Course;
import com.yuan.databind.entity.CourseList;
import com.yuan.databind.entity.CourseMap;
import com.yuan.databind.entity.CourseSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class DataBindController {
    @Autowired
    private CourseDao courseDao;

    //1、绑定基本类型的数据
    @RequestMapping(value = "/baseType")
    @ResponseBody //将数据直接响应给客户端，而不会跳转到JSP页面
    public String baseType(@RequestParam(value = "id") int id)
    {
        return "id:"+id;
    }
    //2、绑定包装类的数据(Integer是int的包装类)
    @RequestMapping(value = "/packageType")
    @ResponseBody
    public String packageType(@RequestParam(value = "id") Integer id)
    {
        return "id:"+id;
    }


    //3、绑定数组类型的数据
    @RequestMapping(value = "/arrayType")
    @ResponseBody //加了@ResponseBody注解表示响应的内容显示也在这里
    public String arrayType(String[] name)
    {
        StringBuffer strings=new StringBuffer();
        for (String item:name)
        {
            strings.append(item).append(" ");
        }
        return "name:"+strings.toString();
    }
    //4、绑定对象类型的数据
    //因为这里的响应的内容显示是在index.jsp中，所以不用加@ResponseBody注解
    @RequestMapping(value = "/pojoType")
    public ModelAndView pojoType(Course course)
    {
        courseDao.add(course);//将从课程添加页面传来的课程信息添加到后台
        Collection<Course> courses=courseDao.getAllCourses();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("courses",courses);
        return modelAndView;
    }
    /*
    List类型数据绑定的时候不能直接使用List集合，而要使用其的包装类进行绑定
    注意：这里真正绑定的是包装类中名为courses的List集合和addList.jsp中的输入框的name属性值相绑定，
         因此这两个地方的List集合的名称必须一致
     */
    //5、绑定List集合类型的数据
    @RequestMapping(value = "/listType")
    public ModelAndView listType(CourseList courseList)
    {
        //遍历list集合的所有课程对象，并一个一个添加课程
        for (Course course:courseList.getCourses())
        {
            courseDao.add(course);//添加课程
        }
        Collection<Course> courses=courseDao.getAllCourses();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("courses",courses);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /*
    Map集合的数据不能直接通过Map集合来绑定，而应该通过Map集合的包装类来绑定；
    注意：这里真正绑定的是包装类中名为courses的Map集合和addMap.jsp中的输入框的name属性值相绑定，
         因此这两个地方的map集合的名称必须一致
     */
    //6、Map集合类型的数据绑定
    @RequestMapping(value = "/mapType")
    public ModelAndView mapType(CourseMap courseMap)
    {
        //遍历Map集合中的Key值
        for (String key:courseMap.getCourses().keySet())
        {
            //先通过courseMap这个包装类对象的getCourses方法获取到Map集合对象，然后通过key值获取其对应的value值
            Course course=courseMap.getCourses().get(key);
            courseDao.add(course);
        }
        Collection<Course> courses=courseDao.getAllCourses();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("courses",courses);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    //7、绑定Set集合类型的数据(注意：包装类中的set集合必须要初始化，这是与map和list的典型区别)
    @RequestMapping(value = "/setType")
    public ModelAndView setType(CourseSet courseSet)
    {
        //遍历Set集合中的元素
        for (Course course:courseSet.getCourses())
        {
            courseDao.add(course);
        }
        Collection<Course> courses=courseDao.getAllCourses();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("courses",courses);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    //8、绑定Json类型的数据
    /*
    为了实现将前端的Json数据封装到Course类的对象中，必须在Course类型名前加上@RequestBody注解
     */
    @RequestMapping(value = "/jsonType")
    @ResponseBody
    public Course jsonType(@RequestBody Course course)
    {
        course.setPrice(course.getPrice()+100);
        return course;
    }

}
