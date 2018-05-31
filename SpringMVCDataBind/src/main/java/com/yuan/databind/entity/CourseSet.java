package com.yuan.databind.entity;

import java.util.HashSet;
import java.util.Set;

//Set集合的包装类
public class CourseSet {
    //定义一个Set集合，并实例化
    private Set<Course> courses=new HashSet<Course>();
    /*
    在构造函数中对set集合进行初始化，即添加几个对象到set集合中，
    这是必须的，不添加就不能完成set集合类的数据绑定
     */
    public CourseSet()
    {
        //先实例化一个set集合对象，才能调用set集合的add方法
        courses.add(new Course());//加入一个课程的空对象
        courses.add(new Course());
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
