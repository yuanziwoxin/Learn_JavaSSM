package com.yuan.sm.service.Impl;

import com.yuan.sm.dao.DepartmentDao;
import com.yuan.sm.entity.Department;
import com.yuan.sm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Resource(name = "departmentDao")
    private DepartmentDao departmentDao;

    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public void remove(Integer id) {
        departmentDao.delete(id);
    }

    public Department findById(Integer id) {

        return departmentDao.selectById(id);
    }

    public List<Department> findAll() {
        return departmentDao.selectAll();
    }
}
