package com.yuan.pagehelper.dao;

import com.yuan.pagehelper.entity.User;

import java.util.List;

public interface UserDao {
    /**
     * 通过当前页起始的记录的索引和每页记录数获取该页的所有用户信息
     * @param startIndex 当前页开始的记录的索引
     * @param pageSize 每页的记录条数
     * @return
     * @throws Exception
     *
     *   limit startIndex,pageSize;
     */
    List<User> getAllUsers(int startIndex, int pageSize) throws Exception;

    /**
     * 获取总的记录条数
     * @return
     * @throws Exception
     */
    int getTotalRecords() throws Exception;


}
