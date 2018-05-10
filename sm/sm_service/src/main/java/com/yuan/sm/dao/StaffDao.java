package com.yuan.sm.dao;

import com.yuan.sm.entity.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("staffDao")
public interface StaffDao {
    /**
     * 添加用户
     * @param staff
     */
    public void insert(Staff staff);

    /**
     * 修改用户
     * @param staff
     */
    public void update(Staff staff);

    /**
     * 删除用户
     * @param id
     */
    public void delete(Integer id);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    public Staff selectById(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    public List<Staff> selectAll();
}
