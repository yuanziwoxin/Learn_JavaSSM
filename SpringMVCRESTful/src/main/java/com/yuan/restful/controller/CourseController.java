package com.yuan.restful.controller;

import com.yuan.restful.dao.CourseDao;
import com.yuan.restful.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CourseController {
    @Autowired
    private CourseDao courseDao;

    /**
     * 添加课程
     * @return
     */
    @PostMapping(value = "/add")
    public String add(Course course)
    {
        courseDao.add(course);
        return "redirect:/getAll";
    }

    /**
     * 查询所有的课程
     * @return
     */
    @GetMapping(value = "/getAll")
    public ModelAndView getAll()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("courses",courseDao.getAll());
        return modelAndView;
    }

    /**
     * 根据id查询课程
     * @param id
     * @return
     */
    @GetMapping(value = "/getById/{id}")
    public ModelAndView getById(@PathVariable("id") int id)
    {
        Course course=courseDao.getById(id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("course",course);
        return modelAndView;
    }

    /**
     * 修改课程
     * @param course
     * @return
     */
    @PutMapping(value = "/update")
    public String update(Course course)
    {
        courseDao.update(course);
        return "redirect:/getAll";
    }

    /**
     * 删除课程
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id)
    {
        courseDao.delete(id);
        return "redirect:/getAll";
    }
}
