package com.yuan.sm.service.Impl;

import com.yuan.sm.dao.SelfDao;
import com.yuan.sm.dao.StaffDao;
import com.yuan.sm.entity.Staff;
import com.yuan.sm.service.SelfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("selfService")
public class SelfServiceImpl implements SelfService {

    @Resource(name = "selfDao")
    private SelfDao selfDao;

    @Resource(name = "staffDao")
    private StaffDao staffDao;

    public Staff login(String account, String password) {

        Staff staff=selfDao.selectByAccount(account);
        //如果不存在该账户，则返回null
        if (staff==null) return null;
        //如果输入的该账户的密码和数据库的密码一致，则返回该用户对象
        if (staff.getPassword().equals(password))
            return staff;
        //否则返回null（密码不一致）
        return null;
    }

    public void changePassword(Integer id, String newPassword) {

        selfDao.updatePsw(id,newPassword);//修改密码
    }
}
