package com.yuan.ioc.demo3;

import org.springframework.stereotype.Repository;

@Repository("catagoryDao")
public class CatagoryDao {

    public void save(){
        System.out.println("CatagoryDao中的save方法....");
    }
}
