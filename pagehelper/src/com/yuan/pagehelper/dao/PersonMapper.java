package com.yuan.pagehelper.dao;

import com.yuan.pagehelper.entity.Person;

import java.util.List;

public interface PersonMapper {
    /**
     * 获取所有人员信息
     * @return
     */
    List<Person> getAllPersons();
}
