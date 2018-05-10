package com.yuan.sm.controller;

import com.yuan.sm.entity.Department;
import com.yuan.sm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("departmentController")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    //   /department/list.do    /department_list.jsp

    /**
     * 将部门信息进行列表显示
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Department> list=departmentService.findAll();
            request.setAttribute("LIST",list);//将list中的内容传递给request中，对应名为LIST的键
        /*
         * 然后转发到department_list.jsp页面，
         * 注意：（1）这里不能直接用重定向，因为重定向不能传值；
         *      （2）"../department_list.jsp"表示一个相对路径，相对于控制层（list.do）,
         *        即当前目录是在/department下，在上一级就是在根目录下（即第一个”/“下），而
         *        department_list.jsp就是在根目录下
         */
            request.getRequestDispatcher("../department_list.jsp").forward(request,response);
    }

    /**
     * 打开添加部门记录页面
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void toAdd(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //因为这里不用传值，所以可以直接使用重定向,重定向到department_add.jsp页面
        //response.sendRedirect("../department_add.jsp");
        //这里不是重定向，而是直接转发到这个添加页面（department_add.jsp）
        request.getRequestDispatcher("../department_add.jsp").forward(request,response);

    }

    /**
     * 添加部门记录
     * @param request
     * @param response
     * @throws IOException
     */
    public void add(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String name=request.getParameter("name");//从department_add.jsp中的提交的form表单中“名称”输入框获取填写的名称；
        String address=request.getParameter("address");

        Department department=new Department();//实例化一个Department对象
        department.setName(name);
        department.setAddress(address);

        departmentService.add(department);

        //添加完之后，重定向到list.do(不是直接到department_list.jsp),返回部门列表页面
        response.sendRedirect("list.do");
    }

    /**
     * 转发到编辑部门信息页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void toEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Integer id=Integer.parseInt(request.getParameter("id"));//获取id(从请求的URL路径中获取)
        Department department=departmentService.findById(id);//通过id找到要修改的这条部门记录
        request.setAttribute("DEPARTMENT",department);//把这条部门记录（即一个Department对象）传递到request中，对应名称为DEPARTMENT的键
        request.getRequestDispatcher("../department_edit.jsp").forward(request,response);
    }

    /**
     * 编辑部门记录
     * @param request
     * @param response
     * @throws IOException
     */
    public void edit(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Integer id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String address=request.getParameter("address");

        Department department=new Department();//实例化一个Department对象
        department.setId(id);
        department.setName(name);
        department.setAddress(address);

        departmentService.edit(department);//将修改的部门信息写入数据库

        //重定向到list.do(即使它显示部门列表信息)
        response.sendRedirect("list.do");
    }

    /**
     * 删除一条部门记录
     * @param request
     * @param response
     * @throws IOException
     */
    public void remove(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Integer id=Integer.parseInt(request.getParameter("id"));//获取id(从URL中获取)

        departmentService.remove(id);//根据id从数据库中删除这条记录

        response.sendRedirect("list.do");//删除之后还是回到部门列表页面

    }


}
