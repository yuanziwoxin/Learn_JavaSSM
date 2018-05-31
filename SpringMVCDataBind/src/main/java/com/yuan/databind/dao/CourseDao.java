package com.yuan.databind.dao;

import com.yuan.databind.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CourseDao {
    private Map<Integer,Course> courses=new HashMap<Integer, Course>();

    //添加课程
    public void add(Course course)
    {
        courses.put(course.getId(),course);
    }
    //查询所有课程
    public Collection<Course> getAllCourses()
    {
        return courses.values();//返回Map集合的所有value值，以collection的形式返回
    }
}
