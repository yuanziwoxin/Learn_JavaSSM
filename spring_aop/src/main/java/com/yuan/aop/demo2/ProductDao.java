package com.yuan.aop.demo2;

public class ProductDao {

    public void save(){
        System.out.println("增加用户信息....");
    }

    public void update(){
        System.out.println("修改用户信息....");
    }

    public void delete(){
        System.out.println("删除用户信息....");
    }

    public void find(){
        System.out.println("查询用户信息....");
    }

}
