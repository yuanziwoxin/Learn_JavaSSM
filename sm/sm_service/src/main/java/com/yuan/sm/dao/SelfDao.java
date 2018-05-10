package com.yuan.sm.dao;

import com.yuan.sm.entity.Staff;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用于实现用户登录的持久层
 */
@Repository("selfDao")
public interface SelfDao {
    /**
     * 根据账户查询用户记录
     * @param account
     * @return 返回一个用户对象
     *
     * 注：这里不采用SQL拼接的方式，而是返回一个用户对象，
     * 这样登录时可以通过判断输入的密码和返回的用户对象的密码是否匹配，
     * 这种方法可以防止SQL注入。
     *
     */
    public Staff selectByAccount(String account);

    /**
     * 修改密码
     * @param id
     * @param newPassword
     *   参数前使用"@param"，是为了通过注解的形式传递多参数
     */
    public void updatePsw(@Param("id") Integer id,@Param("newPassword") String newPassword);
}
