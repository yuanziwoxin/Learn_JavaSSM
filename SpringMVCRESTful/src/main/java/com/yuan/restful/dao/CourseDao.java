package com.yuan.restful.dao;

import com.yuan.restful.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CourseDao {
    //用Map作结果集
    private Map<Integer,Course> courses=new HashMap<Integer, Course>();

    /**
     * 添加课程
     * @param course
     */
    public void add(Course course)
    {
        courses.put(course.getId(),course);
    }

    /**
     * 查询所有课程
     * @param
     */
    public Collection<Course> getAll()
    {
       return courses.values();//获取Map集合的所有value值
    }

    /**
     * 通过id查询课程
     * @param id
     * @return
     */
    public Course getById(int id)
    {
        return courses.get(id);
    }

    /**
     * 根据id修改课程
     * @param course
     */
    public void update(Course course)
    {
        courses.put(course.getId(),course);
    }

    /**
     * 删除课程
     * @param id
     */
    public void delete(int id)
    {
        courses.remove(id);
    }
}
