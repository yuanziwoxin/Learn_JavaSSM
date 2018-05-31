package com.yuan.oa.biz;

import com.yuan.oa.entity.Department;

import java.util.List;

//部门管理的业务层接口
public interface DepartmentBiz {
    /**
     * 添加部门
     * @param department
     */
    void add(Department department);

    /**
     * 修改部门
     * @param department
     */
    void edit(Department department);

    /**
     * 根据部门编号删除部门
     * @param dname 部门编号
     */
    void remove(String dname);

    /**
     * 根据部门编号查询部门
     * @param dname 部门编号
     * @return
     */
    Department getOne(String dname);

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAll();
}
