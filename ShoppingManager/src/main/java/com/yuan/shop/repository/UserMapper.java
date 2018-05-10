package com.yuan.shop.repository;

import com.yuan.shop.bean.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * UserMapper 数据访问类
 *
 */
@Repository("userMapper")
public interface UserMapper {

    /**
     * 根据登录名获取用户对象
     * @param loginName
     * @return
     */
    @Select("select * from ec_user where login_name=#{loginName}")
    User getUserByloginName(String loginName);
}