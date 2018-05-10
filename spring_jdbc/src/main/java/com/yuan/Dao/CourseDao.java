package com.yuan.Dao;

import com.yuan.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CourseDao {
    /**
     * 插入一条课程记录
     * @param course
     */
    public void insert(Course course);

    /**
     * 修改某条课程记录
     * @param course
     */
    public void update(Course course);

    /**
     * 根据课程编号删除某条课程记录
     * @param id
     */
    public void delete(int id);

    /**
     * 根据课程编号查询课程记录
     * @param id
     * @return
     */
    public Course selectOne(int id);

    /**
     * 查询所有课程记录
     * @return
     */
    public List<Course> selectAll();
}
