package com.yuan.pagehelper.service;

import com.yuan.pagehelper.util.PageUtil;

public interface UserService {
    /**
     * 根据当前页数来获取分页相关的数据
     * @param currentNum
     * @return
     */
    PageUtil getAllUsers(String currentNum) throws Exception;
}
