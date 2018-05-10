package com.yuan.aspectj.demo2;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public void save() {
        System.out.println("保存客户信息....");
    }

    @Override
    public String update() {
        System.out.println("修改客户信息....");
        return "Updated Successfully!";
    }

    @Override
    public void delete() {
        System.out.println("删除客户信息....");
    }

    @Override
    public void findone() {
        System.out.println("查找一个客户信息....");
        //int i=1/0;
    }

    @Override
    public void findall() {
        System.out.println("查找所有客户信息....");
        //int i=1/0;
    }
}
