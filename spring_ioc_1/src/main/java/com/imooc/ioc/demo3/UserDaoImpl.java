package com.imooc.ioc.demo3;

public class UserDaoImpl implements UserDao {

    @Override
    public void findall() {
        System.out.println("查询操作...");
    }

    @Override
    public void save() {
        System.out.println("增加操作...");
    }

    @Override
    public void update() {
        System.out.println("修改操作...");
    }

    @Override
    public void delete() {
        System.out.println("删除操作...");
    }
}
