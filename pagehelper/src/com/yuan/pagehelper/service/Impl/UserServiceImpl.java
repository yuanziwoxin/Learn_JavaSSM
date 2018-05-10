package com.yuan.pagehelper.service.Impl;

import com.yuan.pagehelper.dao.Impl.UserDaoImpl;
import com.yuan.pagehelper.dao.UserDao;
import com.yuan.pagehelper.entity.User;
import com.yuan.pagehelper.service.UserService;
import com.yuan.pagehelper.util.PageUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();

    @Override
    public PageUtil getAllUsers(String currentNum) throws Exception {

        int currentPageNum = 1;//假设默认当前页数为1；

        if (null!=currentNum && !currentNum.trim().equals(""))
        { //如果传递过来的页数不为null且不为空字符串，则当前页数就为该值
            currentPageNum=Integer.parseInt(currentNum);
        }

        int totalRecords=userDao.getTotalRecords();//获取总的记录数

        //传入当前页码和总记录数获取PageUtil对象
        PageUtil pageUtil=new PageUtil(currentPageNum,totalRecords);
        /*
        通过PageUtil对象可以获得当前页的起始记录索引和每页的记录条数,
        并将这两个参数传入userDao的getAllUsers方法（获取当前页的所有记录信息），
        然后获取到当前页的所有记录信息；
         */
        List<User> users=userDao.getAllUsers(pageUtil.getStartIndex(),pageUtil.getPageSize());

        //将获取的当前页所有记录信息设置到PageUtil对象的records属性中
        pageUtil.setRecords(users);

        return pageUtil;
    }
}
