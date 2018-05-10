package com.yuan.Impl;

import com.yuan.Dao.StudentDao;
import com.yuan.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository //此注解应写在Dao的实现类上
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Student student) {
        String sql="insert into student(name,sex,born) values(?,?,?)";
        jdbcTemplate.update(sql,student.getName(),student.getSex(),student.getBorn());
    }

    @Override
    public void update(Student student) {
        String sql="update student set name=?,sex=?,born=? where id=?";
        jdbcTemplate.update(sql,student.getName(),student.getSex(),student.getBorn(),student.getId());
    }

    @Override
    public void delete(int id) {
        String sql="delete from student where id=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public Student selectOne(int id) {
        String sql="select * from student where id=?";
        //return jdbcTemplate.queryForObject(sql,Student.class,id);//与下面的写法有何区别？
        return (Student) jdbcTemplate.queryForObject(sql,new StudentRowMapper(),id);//注意这里的写法
    }

    @Override
    public List<Student> selectAll() {
        String sql="select * from student";
        return jdbcTemplate.query(sql,new StudentRowMapper());
    }

    public class StudentRowMapper implements RowMapper<Student>{

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
