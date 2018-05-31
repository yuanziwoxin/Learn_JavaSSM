package com.yuan.oa.controller;

import com.yuan.oa.biz.DepartmentBiz;
import com.yuan.oa.entity.Department;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

@Controller("departmentController")
@RequestMapping("/department")
public class DepartmentController {
    @Resource(name = "departmentBiz")
    private DepartmentBiz departmentBiz;

    //部门列表显示
    @RequestMapping("/list")
    public String list(Map<String,Object> map)
    {
        map.put("list",departmentBiz.getAll());
        return "department_list";
    }

    //跳转到添加部门
    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map)
    {
        map.put("department",new Department());
        return "department_add";
    }

    //添加部门
    @RequestMapping("/add")
    public String add(Department department)
    {
        departmentBiz.add(department);
        return "redirect:list";
    }

    //跳转到修改用户
    @RequestMapping(value = "/to_update",params = "dname")
    public String toUpdate(String dname,Map<String,Object> map)
    {
        map.put("department",departmentBiz.getOne(dname));
        return "department_update";
    }

    //添加部门
    @RequestMapping("/update")
    public String update(Department department)
    {
        departmentBiz.edit(department);
        return "redirect:list";
    }

    //删除部门
    @RequestMapping(value = "/remove",params = "dname")
    public String remove(String dname)
    {
        departmentBiz.remove(dname);
        return "redirect:list";
    }


}
