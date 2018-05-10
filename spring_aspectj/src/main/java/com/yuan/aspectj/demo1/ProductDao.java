package com.yuan.aspectj.demo1;

public class ProductDao {

    public void save(){
        System.out.println("保存商品....");
        //定义一个异常
        //int i=1/0;
    }

    public String update(){
        System.out.println("修改商品....");
        return "Updated Successfully!";
    }

    public void delete(){
        System.out.println("删除商品....");
    }

    public void findone(){
        System.out.println("查找一种商品....");
        //定义一个异常
        //int i=1/0;
    }

    public void findall(){
        System.out.println("查找所有商品....");
    }

}
