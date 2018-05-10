package com.yuan.mybatisdemo.test;

import com.yuan.mybatisdemo.dao.PersonMapper;
import com.yuan.mybatisdemo.entity.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class ParameterTest {

    //获取当前mybatis对应的SqlSessionFactory这种环境（？）
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

    /**
     * 测试mybatis传递单个参数（mybatis会自动进行参数的赋值）
     */
    @Test
    public void delPerson(){
        //借助getSqlSessionFactory()方法获取SqlSession，即获取到一次连接
        //SqlSession相当于和数据库的一次连接，而SqlSessionFactory相当于一个数据库
        SqlSession sqlSession=getSqlSessionFactory().openSession();

        //通过 可以获取到PersonMapper的一个动态代理类，代理类返回的类型是PersonMapper类
        PersonMapper personMapper=sqlSession.getMapper(PersonMapper.class);
        //调用personMapper接口的方法
        personMapper.deletePerson(5);
        //借助sqlSession提交事务
        sqlSession.commit();

    }
    /**
     * 测试:
     * 1、mybatis默认传递多个参数的方式（mybatis会自动进行参数的赋值）；
     * 2、使用javaBean传递参数的形式
     */
    @Test
    public void findPersonByNameAndGender(){
        //借助getSqlSessionFactory()方法获取SqlSession，即获取到一次连接
        //SqlSession相当于和数据库的一次连接，而SqlSessionFactory相当于一个数据库
        SqlSession sqlSession=this.getSqlSessionFactory().openSession();

        //通过 可以获取到PersonMapper的一个动态代理类，代理类返回的类型是PersonMapper类
        PersonMapper personMapper=sqlSession.getMapper(PersonMapper.class);
        //调用personMapper接口的方法
        //(1)使用mybatis默认传递多参数的方式
        //Person person=personMapper.getPersonByNameAndGender("lisi","F");
        //(2)使用javaBean的方式传递多参数
        //Person person=personMapper.getPersonByNameAndGender(new Person("zhang","F"));
        //(3)使用Map的方式传递多参数
//        Map<String,Object> param=new HashMap<String,Object>();
//        param.put("username","tom");
//        param.put("gender","M");//注意这里的key值一定要与PersonMapper.xml的SQL语句中的属性值相同

        Person person=personMapper.getPersonByNameAndGender("wanger","F");

        //借助sqlSession提交事务
        sqlSession.commit();

        System.out.println(person);
    }

    /**
     * 测试:
     * 集合类型参数处理
     *  （1）当参数为Collection接口，转换为Map，Map的key为collection；
     *  （2）如果参数类型为List接口，其封装的Map集合的key值除了可以是collection的值外，还有list也可以作为key
     *  （3）如果参数为数组，也会转换为Map，Map的Key值为Array；
     */
    @Test
    public void findPersonByCollection(){
        //借助getSqlSessionFactory()方法获取SqlSession，即获取到一次连接
        //SqlSession相当于和数据库的一次连接，而SqlSessionFactory相当于一个数据库
        SqlSession sqlSession=this.getSqlSessionFactory().openSession();

        //通过 可以获取到PersonMapper的一个动态代理类，代理类返回的类型是PersonMapper类
        PersonMapper personMapper=sqlSession.getMapper(PersonMapper.class);
        /*
        Arrays.asList()是将数组转化为集合，但是不能使用修改集合的相关方法，
        asList的返回对象是一个Arrays内部类，这是适配器模式，只是转化了接口，后台的数据仍然为数组；
         */
        //当参数为Collection接口
        //Person person=personMapper.getPersonByCollection(Arrays.asList(1,2));
        //当参数为数组
        Person person=personMapper.getPersonByCollection(new int[]{1,2,3,4});

        //通过foreach进行迭代
        //List<Person> personList=personMapper.getPersonsByIds(new int[]{1,2,3,4});

        //借助sqlSession提交事务
        sqlSession.commit();

        //System.out.println(personList);
        System.out.println(person);
    }

    /**
     * 测试:
     * MySQL对批量插入的支持
     * （1）借助foreach标签使用insert into table values（），（），....
     * （2）借助数据库连接属性allowMultiQueries=true
     *
     */
    @Test
    public void testaddPersons(){
        SqlSession sqlSession=this.getSqlSessionFactory().openSession();

        PersonMapper personMapper=sqlSession.getMapper(PersonMapper.class);

        List<Person> persons=new ArrayList<Person>();
        for (int i=0;i<1000;i++){
           //设置list集合元素
           Person person= new Person("hadern"+i,"email"+i,"M");
           persons.add(person);//添加元素到list集合中
        }

        personMapper.addPersons(persons);
        //借助sqlSession提交事务
        sqlSession.commit();

    }

    /**
     * 使用基于SqlSession的ExecutorType进行批量插入
     */
    @Test
    public void testaddPerson(){
        //使用基于SqlSession的ExecutorType进行批量插入(注意：openSession括号里面的“ExecutorType.BATCH”参数)
        SqlSession sqlSession=this.getSqlSessionFactory().openSession(ExecutorType.BATCH);

        PersonMapper personMapper=sqlSession.getMapper(PersonMapper.class);

        for (int i=0;i<10000;i++){
            Person person=new Person("cook"+i,"cookemail"+i,"M");
            personMapper.addPerson(person);
        }

        sqlSession.commit();

        sqlSession.close();
    }

}