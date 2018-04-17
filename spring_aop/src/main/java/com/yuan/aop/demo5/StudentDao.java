package com.yuan.aop.demo5;

import org.springframework.stereotype.Repository;

@Repository("studentDao")
public interface StudentDao {
    public void save();

    public void update();

    public void delete();

    public void find();
}
