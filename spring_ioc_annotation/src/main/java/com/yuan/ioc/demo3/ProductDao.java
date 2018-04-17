package com.yuan.ioc.demo3;

import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDao {

    public void save(){
        System.out.println("ProductDao中的save方法...");
    }
}
