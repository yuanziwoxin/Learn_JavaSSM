package com.yuan.databind.entity;

import java.util.Map;

//Map集合的包装类
public class CourseMap {

    private Map<String,Course> courses;


    public Map<String, Course> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, Course> courses) {
        this.courses = courses;
    }
}
