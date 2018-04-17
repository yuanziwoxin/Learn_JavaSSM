package com.yuan.aop.demo3;

public class StudentDaoImpl implements StudentDao{

    @Override
    public void save() {
        System.out.println("增加学生信息....");
    }

    @Override
    public void update() {
        System.out.println("修改学生信息....");
    }

    @Override
    public void delete() {
        System.out.println("删除学生信息....");
    }

    @Override
    public void find() {
        System.out.println("查询学生信息....");
    }
}
