package com.yuan.sm.service.Impl;

import com.yuan.sm.dao.StaffDao;
import com.yuan.sm.entity.Staff;
import com.yuan.sm.service.StaffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("staffService")
public class StaffServiceImpl implements StaffService {

    @Resource(name = "staffDao")
    private StaffDao staffDao;

    public void add(Staff staff) {
        //有些属性是从表现层获取不了的
        staff.setPassword("123456");//设置初始密码
        staff.setWorkTime(new Date());//设置默认的入职时间
        staff.setStatus("正常");//设置默认状态
        staffDao.insert(staff);
    }

    public void edit(Staff staff) {
        staffDao.update(staff);
    }

    public void remove(Integer id) {
        staffDao.delete(id);
    }

    public Staff findById(Integer id) {
        return staffDao.selectById(id);
    }

    public List<Staff> findAll() {
        return staffDao.selectAll();
    }
}
