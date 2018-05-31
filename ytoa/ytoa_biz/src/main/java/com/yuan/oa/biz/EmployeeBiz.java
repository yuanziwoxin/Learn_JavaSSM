package com.yuan.oa.biz;

import com.yuan.oa.entity.Department;
import com.yuan.oa.entity.Employee;

import java.util.List;

//员工管理的业务层接口
public interface EmployeeBiz {
    /**
     * 添加员工
     * @param employee
     */
    void add(Employee employee);

    /**
     * 修改员工
     * @param employee
     */
    void edit(Employee employee);

    /**
     * 根据员工编号删除员工
     * @param ename 员工编号
     */
    void remove(String ename);

    /**
     * 根据员工编号查询员工
     * @param ename 员工编号
     * @return
     */
    Employee getOne(String ename);

    /**
     * 获取所有员工
     * @return
     */
    List<Employee> getAll();
}
