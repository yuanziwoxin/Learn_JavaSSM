package com.yuan.Dao;


import com.yuan.entity.Selection;


import java.util.List;
import java.util.Map;


public interface SelectionDao {

    /**
     * 插入多条选课记录
     * @param selections
     */
    public void insert(List<Selection> selections);

    /**
     * 修改某条选课记录
     * @param selection
     */
    public void update(Selection selection);

    /**
     * 删除某条选课记录
     * @param studentId,courseId
     */
    public void delete(int studentId,int courseId);

    /**
     * 根据学生编号，查询该学生所有选课记录;
     * （查询出来的对象有学生编号、课程编号，不好分清楚，所以用返回Map对象好一点，既有键，又有键值）
     * @param studentId
     */
    public List<Map<String,Object>> selectByStudentId(int studentId);

    /**
     * 根据课程编号，查询该课程所有选课记录;
     * （查询出来的对象有学生编号、课程编号，不好分清楚，所以用返回Map对象好一点，既有键，又有键值）
     * @return
     */
    public List<Map<String,Object>> selectByCourseId(int courseId);
}
