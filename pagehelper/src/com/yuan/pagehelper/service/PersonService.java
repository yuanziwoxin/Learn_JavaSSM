package com.yuan.pagehelper.service;

import com.yuan.pagehelper.entity.Person;

import java.util.List;

public interface PersonService {
    /**
     * 获取所有人员信息（用于使用PageHelper插件进行分页）
     * @return
     */
    List<Person> getAllPersonsByPageHelper();
}
