package com.yuan.oa.dao;

        import com.yuan.oa.entity.Department;
        import com.yuan.oa.entity.Employee;
        import org.apache.ibatis.annotations.Param;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository("employeeDao")
public interface EmployeeDao {
    /**
     * 添加员工
     * @param employee
     */
    void insert(Employee employee);

    /**
     * 修改员工
     * @param employee
     */
    void update(Employee employee);

    /**
     * 根据员工编号删除员工
     * @param ename
     */
    void delete(String ename);

    /**
     * 根据员工编号查询员工
     * @param ename
     * @return
     */
    Employee selectOne(String ename);

    /**
     * 查询所有员工
     * @return
     */
    List<Employee> selectAll();

    /**
     * 根据部门编号和职务查询员工
     * @param dname 部门编号
     * @param post 职务
     * @return
     */
    List<Employee> selectByDepartmentAndPost(@Param("dname") String dname,@Param("post") String post);
}
