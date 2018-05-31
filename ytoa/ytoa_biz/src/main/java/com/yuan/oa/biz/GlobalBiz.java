package com.yuan.oa.biz;

import com.yuan.oa.entity.Employee;

//主要处理登录、修改密码等业务
public interface GlobalBiz {
    /**
     * 用户登录
     * @param ename 用户编号
     * @param password 密码
     * @return
     */
    Employee login(String ename,String password);

    /**
     * 修改用户密码
     * @param employee
     */
    void changePassword(Employee employee);
}
