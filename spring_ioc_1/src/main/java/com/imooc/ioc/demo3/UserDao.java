package com.imooc.ioc.demo3;

public interface UserDao {
    /**
     * 查询
     */
    public void findall();

    /**
     * 增加
     */
    public void save();

    /**
     * 修改
     */
    public void update();

    /**
     * 删除
     */
    public void delete();
}
