package com.yuan.ioc.demo1;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao {
    public void save(){
        System.out.println("Dao中的save方法......");
    }
}
