package com.yuan.Impl;

import com.yuan.Dao.SelectionDao;
import com.yuan.entity.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SelectionDaoImpl implements SelectionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(List<Selection> selections) {
        String sql = "insert into selection(student,course,selection_time,score) values(?,?,?,?)";
        List<Object[]> list = new ArrayList<Object[]>();
        //遍历list集合中的所有Selection对象
        for (Selection selection : selections) {
            //定义一个大小为4个元素的数组，用于存储接受遍历的每个对象4个属性的值；
            Object[] objs = new Object[4];//4表示定义一个有4个元素的数组；
            objs[0] = selection.getStudentId();//将该对象的studentId的值存储到第1个数组元素中;
            objs[1] = selection.getCourseId();
            objs[2] = selection.getSelection_time();
            objs[3] = selection.getScore();
            list.add(objs);//将Object数组对象添加到list集合中
        }
        jdbcTemplate.batchUpdate(sql, list);
    }

    @Override
    public void update(Selection selection) {
        String sql="update selection set selection_time=?,score=? where student=?,course=?";
        jdbcTemplate.update(sql,selection.getSelection_time(),selection.getScore(),selection.getStudentId(),selection.getCourseId());
    }

    @Override
    public void delete(int studentId,int courseId) {
        String sql="delete from selection where student=? and course=?";
        jdbcTemplate.update(sql,studentId,courseId);
    }

    @Override
    public List<Map<String, Object>> selectByStudentId(int studentId) {
        String sql="select st.*,stu.name sname,c.name cname from selection st"+
                "left join student stu on st.student=stu.id"+
                "left join course c on st.course=c.id"+
                "where student=?";
        List<Map<String,Object>> selectionList=jdbcTemplate.queryForList(sql,studentId);
        return selectionList;
    }

    @Override
    public List<Map<String, Object>> selectByCourseId(int courseId) {
        String sql="select st.*,stu.name sname,c.name cname from selection st"+
                "left join student stu on st.student=stu.id"+
                "left join course c on st.course=c.id"+
                "where course=?";
        List<Map<String,Object>> selectionList=jdbcTemplate.queryForList(sql,courseId);
        return selectionList;
    }

    public class SelectionRowMapper implements RowMapper<Selection> {

        @Override
        public Selection mapRow(ResultSet resultSet, int i) throws SQLException {
            Selection selection=new Selection();
            selection.getStudentId();
            selection.getCourseId();
            selection.getSelection_time();
            selection.getScore();
            return selection;
        }
    }
}
