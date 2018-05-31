package com.yuan.oa.controller;

import com.yuan.oa.biz.DepartmentBiz;
import com.yuan.oa.biz.EmployeeBiz;
import com.yuan.oa.entity.Employee;
import com.yuan.oa.global.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

@Controller("employeeController")
@RequestMapping("/employee")
public class EmployeeController {
    @Resource(name = "departmentBiz")
    private DepartmentBiz departmentBiz;

    @Resource(name = "employeeBiz")
    private EmployeeBiz employeeBiz;

    //部门列表显示
    @RequestMapping("/list")
    public String list(Map<String,Object> map)
    {
        map.put("list",employeeBiz.getAll());
        return "employee_list";
    }

    //跳转到添加部门
    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map)
    {
        map.put("employee",new Employee());
        map.put("dlist",departmentBiz.getAll());
        map.put("plist",Constant.getPosts());
        return "employee_add";
    }

    //添加部门
    @RequestMapping("/add")
    public String add(Employee employee)
    {
        employeeBiz.add(employee);
        return "redirect:list";
    }

    //跳转到修改用户
    @RequestMapping(value = "/to_update",params = "ename")
    public String toUpdate(String ename,Map<String,Object> map)
    {
        map.put("employee",employeeBiz.getOne(ename));
        map.put("dlist",departmentBiz.getAll());
        map.put("plist",Constant.getPosts());
        return "employee_update";
    }

    //添加部门
    @RequestMapping("/update")
    public String update(Employee employee)
    {
        employeeBiz.edit(employee);
        return "redirect:list";
    }

    //删除部门
    @RequestMapping(value = "/remove",params = "ename")
    public String remove(String ename)
    {
        employeeBiz.remove(ename);
        return "redirect:list";
    }


}
