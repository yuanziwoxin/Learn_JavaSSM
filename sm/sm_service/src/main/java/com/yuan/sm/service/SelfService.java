package com.yuan.sm.service;


import com.yuan.sm.entity.Staff;


public interface SelfService {
    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    public Staff login(String account, String password);

    /**
     * 修改用户密码
     * @param id
     * @param newPassword
     */
    public void changePassword(Integer id,String newPassword);
}
