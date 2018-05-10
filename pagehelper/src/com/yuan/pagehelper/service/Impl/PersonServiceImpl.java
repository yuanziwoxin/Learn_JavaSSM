package com.yuan.pagehelper.service.Impl;

import com.yuan.pagehelper.dao.PersonMapper;
import com.yuan.pagehelper.entity.Person;
import com.yuan.pagehelper.service.PersonService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class PersonServiceImpl implements PersonService {


    //获取当前mybatis对应的SqlSessionFactory这种环境
    public static SqlSessionFactory sqlSessionFactory = null;

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            String resource = "mybatis-config.xml";
            try {
                Reader reader = Resources.getResourceAsReader(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }


    @Override
    public List<Person> getAllPersonsByPageHelper() {

        SqlSession sqlSession=this.getSqlSessionFactory().openSession();

        PersonMapper personMapper =sqlSession.getMapper(PersonMapper.class);

        List<Person> persons= personMapper.getAllPersons();//查询所有人员的信息

        return persons;
    }
}
