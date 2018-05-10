package com.yuan.pagehelper.dao.Impl;

import com.yuan.pagehelper.dao.BaseDao;
import com.yuan.pagehelper.dao.UserDao;
import com.yuan.pagehelper.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    //实例化一个QueryRunner对象
    QueryRunner queryRunner=new QueryRunner();

    @Override
    public List<User> getAllUsers(int startIndex, int pageSize) throws Exception {
        List<User> users=null;

        String sql="select * from t_user limit ?,?";
        //查询返回的结果集是一个List集合，以new Object[]{startIndex,pageSize}的方式传入这两个参数
        users=queryRunner.query(this.getConnection(),sql,new BeanListHandler<User>(User.class),new Object[]{startIndex,pageSize});

        return users;
    }

    @Override
    public int getTotalRecords() throws Exception {

        int result=0;

        Connection connection=this.getConnection();//获取连接

        String sql="select count(*) count from t_user";

        PreparedStatement preparedStatement=connection.prepareStatement(sql);//获取PreparedStatement对象

        ResultSet resultSet=preparedStatement.executeQuery();//获取结果集

        if (resultSet.next())//如果有结果集的话
        {
            result=resultSet.getInt("count");//则获取查询到的结果集中count这个属性值
        }

        return result;
    }
}
