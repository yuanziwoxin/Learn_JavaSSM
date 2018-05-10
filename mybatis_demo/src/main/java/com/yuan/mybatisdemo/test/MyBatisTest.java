package com.yuan.mybatisdemo.test;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuan.mybatisdemo.dao.PersonMapper;
import com.yuan.mybatisdemo.entity.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MyBatisTest {

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

    @Test
    public void getAllPersons(){
        SqlSession sqlSession=this.getSqlSessionFactory().openSession();

        PersonMapper personMapper=sqlSession.getMapper(PersonMapper.class);

        /*
        PageHelper插件分页的主要原理是：
        通过插件去过滤这个Executor这个接口，而Executor有一个query方法，
        通过插件对query方法进行拦截，使其在查询时添加一些分页的参数
         */
        //获取第1页，10条内容，默认查询总数为count
        Page<Person> page= PageHelper.startPage(1,10);

        List<Person> persons=personMapper.getAllPersons();
        //PageInfo 有你当前查询的所有数据，以及当前这一页的信息
        PageInfo pageInfo=new PageInfo(persons,5);//navigate pages 表示导航页数

        for (Person person:persons)
        {
            System.out.println(person.getId());
        }

        System.out.println("当前页数："+page.getPageNum());
        System.out.println("总页数："+page.getTotal());
        System.out.println("页大小（每页记录条数）："+page.getPageSize());


        System.out.println("是否是第一页："+pageInfo.isIsFirstPage());
        System.out.println("导航页数："+pageInfo.getNavigatePages());

        int [] nums=pageInfo.getNavigatepageNums();//表示当前页码所在的（导航页）数组
        for (int i=0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
    }
}
