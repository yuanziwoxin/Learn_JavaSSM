package com.yuan.sm.dao;

import com.yuan.sm.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departmentDao")
public interface DepartmentDao {

    /**
     * 添加部门记录
     * @param department
     */
    void insert(Department department);

    /**
     * 修改部门记录
     * @param department
     */
    void update(Department department);

    /**
     * 通过编号删除部门记录
     * @param id
     */
    void delete(Integer id);

    /**
     * 通过编号查询部门记录
     * @param id
     * @return
     */
    Department selectById(Integer id);

    /**
     * 查询所有部门记录
     * @return
     */
    List<Department> selectAll();
}
