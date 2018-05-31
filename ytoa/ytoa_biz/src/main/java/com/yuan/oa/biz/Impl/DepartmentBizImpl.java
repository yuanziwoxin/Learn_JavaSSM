package com.yuan.oa.biz.Impl;

import com.yuan.oa.biz.DepartmentBiz;
import com.yuan.oa.dao.DepartmentDao;
import com.yuan.oa.entity.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("departmentBiz")
public class DepartmentBizImpl implements DepartmentBiz {
    @Resource(name="departmentDao")
    private DepartmentDao departmentDao;

    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public void remove(String dname) {
        departmentDao.delete(dname);
    }

    public Department getOne(String dname) {
        return departmentDao.selectOne(dname);
    }

    public List<Department> getAll() {
        return departmentDao.selectAll();
    }
}
