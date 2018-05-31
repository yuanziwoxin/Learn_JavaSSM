package com.yuan.databind.entity;

import java.util.List;

//List集合的包装类
public class CourseList {

    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
