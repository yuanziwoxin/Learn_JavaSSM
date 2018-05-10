package com.yuan.sm.service;

import com.yuan.sm.entity.Department;

import java.util.List;


public interface DepartmentService {
    /**
     * 新增一条部门记录
     * @param department
     */
    void add(Department department);

    /**
     * 编辑部门记录
     * @param department
     */
    void edit(Department department);

    /**
     * 通过编号，删除部门记录
     * @param id
     */
    void remove(Integer id);

    /**
     * 通过编号，查询部门记录
     */
    Department findById(Integer id);

    /**
     * 查询所有部门记录
     */
    List<Department> findAll();
}
