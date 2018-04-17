package com.yuan.aop.demo1;

public class UserDaoImpl implements UserDao {

    @Override
    public void save() {
        System.out.println("增加用户信息...");
    }

    @Override
    public void update() {
        System.out.println("修改用户信息...");
    }

    @Override
    public void delete() {
        System.out.println("删除用户信息...");
    }

    @Override
    public void find() {
        System.out.println("查询用户信息...");
    }
}
