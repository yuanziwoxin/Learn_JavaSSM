package com.yuan.oa.biz.Impl;

import com.yuan.oa.biz.GlobalBiz;
import com.yuan.oa.dao.EmployeeDao;
import com.yuan.oa.entity.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("globalBiz")
public class GlobalBizImpl implements GlobalBiz {
    @Resource(name = "employeeDao")
    private EmployeeDao employeeDao;

    public Employee login(String ename, String password) {
        Employee employee=employeeDao.selectOne(ename);
        //如果用户编号对应的用户对象不为空，且密码相匹配，则返回一个Employee对象
        if (employee!=null && employee.getPassword().equals(password))
        {
            return employee;
        }
        return null;
    }

    public void changePassword(Employee employee) {
        employeeDao.update(employee);
    }
}
