package com.yuan.Dao;

import com.yuan.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface StudentDao {
    /**
     * 插入一条学生信息
     * @param student
     */
    public void insert(Student student);

    /**
     * 修改学生信息
     * @param student
     */
    public void update(Student student);

    /**
     * 根据学生编号删除学生信息
     * @param id
     */
    public void delete(int id);

    /**
     * 根据学生编号查询学生信息
     * @param id
     * @return
     */
    public Student selectOne(int id);

    /**
     * 查询所有学生信息
     * @return
     */
    public List<Student> selectAll();
}
