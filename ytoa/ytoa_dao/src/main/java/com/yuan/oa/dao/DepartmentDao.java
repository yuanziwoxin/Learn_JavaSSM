package com.yuan.oa.dao;

import com.yuan.oa.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departmentDao")
public interface DepartmentDao {
    /**
     * 添加部门
     * @param department
     */
    void insert(Department department);

    /**
     * 修改部门
     * @param department
     */
    void update(Department department);

    /**
     * 根据部门编号删除部门
     * @param dname
     */
    void delete(String dname);

    /**
     * 根据部门编号查询部门
     * @param dname
     * @return
     */
    Department selectOne(String dname);

    /**
     * 查询所有部门
     * @return
     */
    List<Department> selectAll();
}
