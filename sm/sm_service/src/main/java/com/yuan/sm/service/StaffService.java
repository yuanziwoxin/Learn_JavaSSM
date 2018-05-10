package com.yuan.sm.service;


import com.yuan.sm.entity.Staff;

import java.util.List;

public interface StaffService {
    /**
     * 增加一个用户
     * @param staff
     */
    public void add(Staff staff);

    /**
     * 编辑用户
     * @param staff
     */
    public void edit(Staff staff);

    /**
     * 删除用户
     * @param id
     */
    public void remove(Integer id);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public Staff findById(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    public List<Staff> findAll();
}
