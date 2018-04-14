package com.imooc.ioc.demo1;

public class UserServiceImpl implements UserService {
    //添加属性
    private String name;
    public void sayHello() {
        System.out.println("hello,Spring!"+name);
    }
    //设置属性值
    public void setName(String name){
        this.name=name;
    }
    //获取属性值
    public String getName(){
        return name;
    }
}
