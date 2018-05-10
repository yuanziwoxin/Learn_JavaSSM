package com.yuan.test;

import com.yuan.entity.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用于测试execute方法、update和batchUpdate（批量删除）方法的类
 */
public class Test1 {

    //为了方便我们测试一些方法，这里构建一个初始化块；
    private JdbcTemplate jdbcTemplate;
    {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring.xml");
        jdbcTemplate= (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
    }

    /**
     * execute方法：一般用于创建表、修改表结构
     */
    @Test
    public void testExecute(){
        jdbcTemplate.execute("create table user1(id int,name varchar(20))");
    }

    /**
     * update方法一（对数据增删改）
     */
    @Test
    public void testUpdate1(){
        String sql="insert into student(name,sex) values(?,?)";
        jdbcTemplate.update(sql,new Object[]{"习近平","男"});
    }

    /**
     * update方法二（对数据增删改）
     */
    @Test
    public void testUpdate2(){
        String sql="update student set sex=? where name=?";
        jdbcTemplate.update(sql,"女","奥巴马");
    }

    /**
     * batchUpdate方法一（批量增删改）：执行多条不同SQL语句（多次数据操作）；
     */
    @Test
    public void batchUpdate1(){
        //未使用占位符"?"
        String[] sqls={
              "insert into student(name,sex) values('关羽','男')",
              "insert into student(name,sex) values('刘亦菲','女')",
              "update student set sex='男' where name='奥巴马'"
        };
        jdbcTemplate.batchUpdate(sqls);
    }

    /**
     * batchUpdate方法二（批量增删改）：一条SQL语句（一种数据操作）执行多次
     */
    @Test
    public void batchUpdate2(){

        String sql= "insert into student(name,sex) values(?,?)";

        List<Object[]> list=new ArrayList<Object[]>();
        list.add(new Object[]{"加内特","男"});
        list.add(new Object[]{"詹姆斯","男"});

        jdbcTemplate.batchUpdate(sql,list);//批量插入数据
    }

    /**
     * queryForObject方法一：查询简单数据项（获取一条数据）；
     * 注意：若queryForObject查询出的结果是多行（多条数据），会报异常；
     */
    @Test
    public void testQueryForObject1(){
        String sql="select count(*) from student";
        int count=jdbcTemplate.queryForObject(sql,Integer.class);
        System.out.println(count);
    }
    /**
     * queryForList方法一：查询简单数据项之（获取一条数据）
     * 注意：如果queryForList查询出的结果是多列，会报异常（如“Incorrect column count: expected 1, actual 4”）
     */
    @Test
    public void testQueryForList1(){
        String sql="select name from student where sex=?";
        List<String> stu= jdbcTemplate.queryForList(sql,String.class,"女");
        System.out.println(stu);
    }

    /**
     * queryForMap方法一：查询复杂数据项之（获取一条数据）
     *
     */
    @Test
    public void testQueryForMap1(){
        String sql="select * from student where name =?";
        Map<String,Object> stu= jdbcTemplate.queryForMap(sql,"刘备");
        System.out.println(stu);
    }

    /**
     * 查询复杂对象（封装为Map）之（获取多条数据）：每条数据封装为一个Map；
     */
    @Test
    public void testQueryForList2(){
        String sql="select * from student where sex=?";
        List<Map<String,Object>> students=jdbcTemplate.queryForList(sql,"女");
        System.out.println(students);
    }

    /**
     * 查询复杂对象（封装为实体对象）
     * -RowMapper接口
     * -获取一个数据对象
     */
    //这里每个都使用匿名内部类，稍显麻烦
//    @Test
//    public void testQueryForObject(){
//        final String sql="select * from student where name=?";
//        Student stu=jdbcTemplate.queryForObject(sql, new RowMapper<Student>() {
//            @Override
//            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
//                Student student=new Student();
//                student.setId(resultSet.getInt("id"));
//                student.setName(resultSet.getString("name"));
//                student.setSex(resultSet.getString("sex"));
//                student.setBorn(resultSet.getDate("born"));
//                return student;
//            }
//        }, "刘涛");
//        System.out.println(stu);
//    }
    /**
     * 查询复杂对象（封装为实体对象）
     * -RowMapper接口
     * -获取一个数据对象
     */
    //不使用匿名内部类，把匿名内部类提出来组成一个类，既使代码更加简洁，也使代码复用率提高
    @Test
    public void testQueryForObject(){
        final String sql="select * from student where name=?";
        Student stu=jdbcTemplate.queryForObject(sql, new StudentRowMapper(), "刘涛");
        System.out.println(stu);
    }


    /**
     * 查询复杂对象（封装为实体对象）
     * -RowMapper接口
     * -获取多个数据对象
     */
    @Test
    public void testQueryForEntity(){
        String sql="select * from student where sex=?";
        List<Student> stus=jdbcTemplate.query(sql, new StudentRowMapper(), "男");
        System.out.println(stus);
    }

    //把匿名内部类提出来单独组成一个类，提高代码的重用性
    private class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student=new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setSex(resultSet.getString("sex"));
            student.setBorn(resultSet.getDate("born"));
            return student;
        }
    }


}
