package com.yuan.Impl;

import com.yuan.Dao.CourseDao;
import com.yuan.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository //注意这里的注解是写在Dao的实现类上
public class CourseDaoImpl implements CourseDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Course course) {
        String sql="insert into course(name,score) values(?,?)";
        jdbcTemplate.update(sql,course.getName(),course.getScore());
    }

    @Override
    public void update(Course course) {
        String sql="update course set name=?,score=? where id=?";
        jdbcTemplate.update(sql,course.getName(),course.getScore(),course.getId());
    }

    @Override
    public void delete(int id) {
        String sql="delete from course where id=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public Course selectOne(int id) {
        String sql="select * from course where id=?";
        //Course course=jdbcTemplate.queryForObject(sql,Course.class,id);//与下面有何区别
        Course course= (Course) jdbcTemplate.queryForObject(sql,new CourseRowMapper(),id);
        return course;
    }

    @Override
    public List<Course> selectAll() {
        String sql="select * from course";
        List<Course> list=jdbcTemplate.query(sql,new CourseRowMapper());
        return list;
    }

    public class CourseRowMapper implements RowMapper {

        @Override
        public Course mapRow(ResultSet resultSet, int i) throws SQLException {
            Course course=new Course();
            course.setId(resultSet.getInt("id"));
            course.setName(resultSet.getString("name"));
            course.setScore(resultSet.getInt("score"));
            return course;
        }
    }
}
