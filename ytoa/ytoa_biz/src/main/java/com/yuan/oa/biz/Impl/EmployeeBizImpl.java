package com.yuan.oa.biz.Impl;

import com.yuan.oa.biz.EmployeeBiz;
import com.yuan.oa.dao.EmployeeDao;
import com.yuan.oa.entity.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("employeeBiz")
public class EmployeeBizImpl implements EmployeeBiz {
    @Resource(name="employeeDao")
    private EmployeeDao employeeDao;

    public void add(Employee employee) {
        employee.setPassword("123456");//设置初始密码
        employeeDao.insert(employee);
    }

    public void edit(Employee employee) {
        employeeDao.update(employee);
    }

    public void remove(String ename) {
        employeeDao.delete(ename);
    }

    public Employee getOne(String ename) {
        return employeeDao.selectOne(ename);
    }

    public List<Employee> getAll() {
        return employeeDao.selectAll();
    }
}
